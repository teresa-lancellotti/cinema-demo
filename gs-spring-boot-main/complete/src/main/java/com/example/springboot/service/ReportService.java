package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Room;
import com.example.springboot.model.Show;

public interface ReportService {
	
	public List<Room> readCinemaRooms();
	public List<Show> readCinemaShows();
}
