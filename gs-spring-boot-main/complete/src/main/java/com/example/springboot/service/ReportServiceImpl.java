package com.example.springboot.service;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Room;
import com.example.springboot.model.Show;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("reportService")
public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Value( "${demo.excel.location}" )
	private String excelLocation;
	@Value( "${demo.excel.sheet1.name}" )
	private String excelSheet1;
	@Value( "${demo.excel.sheet2.name}" )
	private String excelSheet2;

	private List<Room> roomsList;

	@Override
	public List<Room> readCinemaRooms() {
		this.roomsList = new ArrayList<Room>();
		Workbook workbook = null;
		Sheet sheet = null;
		Room cinemaRoom = null;

		try {
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(excelLocation));

			// Retrieving the number of sheets in the Workbook
			logger.info("Number of sheets: " + workbook.getNumberOfSheets());

			sheet = workbook.getSheet(excelSheet2);
			logger.info("Title of sheet => " + sheet.getSheetName());

			// Loop through all rows and columns and create Course object
			int index = 0;
			for (Row row : sheet) {
				if (index++ == 0)
					continue;
				cinemaRoom = new Room();

				if (row.getCell(0) != null) {
					if (row.getCell(0).getCellType() == CellType.NUMERIC) {
						cinemaRoom.setId((long) row.getCell(0).getNumericCellValue());
					}
					if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
						cinemaRoom.setName(row.getCell(1).getStringCellValue());
					}
					if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
						cinemaRoom.setCapacity(Integer.valueOf((int) row.getCell(2).getNumericCellValue()));
					}
					if (row.getCell(3) != null) {
						cinemaRoom.setIsIMax(Boolean.valueOf(row.getCell(3).getStringCellValue()));
					}
					this.roomsList.add(cinemaRoom);
				} else {
					logger.error(this.getClass().getSimpleName() + " -> readCinemaRooms ### Cinema room Id missing");
					throw new IOException("Cinema room Id missing");
				}

			}
		} catch (EncryptedDocumentException | IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return this.roomsList;
	}

	@Override
	public List<Show> readCinemaShows() {
		List<Show> cinemaShows = new ArrayList<>();
		Stream<Room> roomsStream = null;
		Workbook workbook = null;
		Sheet sheet = null;
		Cell dateCell = null;
		Show cinemaShow = null;
		List<Room> filteredRoomList = null;
	
		try {
			if (Objects.isNull(this.roomsList)) {
				this.readCinemaRooms();
			}

			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(excelLocation));

			// Retrieving the number of sheets in the Workbook
			logger.info("Number of sheets: " + workbook.getNumberOfSheets());

			sheet = workbook.getSheet(excelSheet1);
			logger.info("Title of sheet => " + sheet.getSheetName());

			// Loop through all rows and columns and create Course object
			int index = 0;
			for (Row row : sheet) {
				if (index++ == 0)
					continue;
				cinemaShow = new Show();

				if (row.getCell(0) != null || row.getCell(1) != null  || row.getCell(2) != null  
						|| row.getCell(3) != null  || row.getCell(4) != null) {
					if (row.getCell(0).getCellType() == CellType.NUMERIC) {
						cinemaShow.setId((long) row.getCell(0).getNumericCellValue());
					}
					if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
						cinemaShow.setTitle(row.getCell(1).getStringCellValue());
					}
	
					dateCell = row.getCell(2);
					if (dateCell != null && DateUtil.isCellDateFormatted(dateCell)) {
					    cinemaShow.setTime(dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).getHour() 
					    				 + ":" + dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).getMinute());
					}
					
					dateCell = row.getCell(3);
					if (dateCell != null && DateUtil.isCellDateFormatted(dateCell)) {
						cinemaShow.setFrom(dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
								.toLocalDate());
					}
					dateCell = row.getCell(4);
					if (dateCell != null && DateUtil.isCellDateFormatted(dateCell)) {
						cinemaShow.setTo(dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
								.toLocalDate());
					}
					
					if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
						roomsStream = this.roomsList.stream();
						filteredRoomList = roomsStream.filter(r -> r.getId() == Long.valueOf((long) row.getCell(5).getNumericCellValue()))
											  .toList();
						cinemaShow.setRoom(filteredRoomList.size() > 0 ? filteredRoomList.get(0) : null);
					}
	
					if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.NUMERIC) {
						cinemaShow.setDuration(Long.valueOf((long) row.getCell(6).getNumericCellValue()));
					}
					cinemaShows.add(cinemaShow);
				} else {
					logger.error(this.getClass().getSimpleName() + " -> readCinemaShows ### Missing one or more important show info: id, title. time or scheduled period");
					throw new IOException("Missing one or more important show info: id, title. time or scheduled period");
				}
			}
		} catch (EncryptedDocumentException | IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return cinemaShows;
	}

}
