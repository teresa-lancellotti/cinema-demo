package com.example.springboot.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Outcome;
import com.example.springboot.model.OutcomeCode;
import com.example.springboot.model.Show;
import com.example.springboot.model.ShowsFound;
import com.example.springboot.service.ReportService;
import com.example.springboot.util.UtilityFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin
public class ShowApiController implements ShowApi {

	private static final Logger log = LoggerFactory.getLogger(ShowApiController.class);
	private DateTimeFormatter formatDateString = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private ReportService reportService;

	private final HttpServletRequest request;

	public ShowApiController(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public ResponseEntity<ShowsFound> getShowsByFromTo(@NotNull @Valid String showDateFrom, @Valid String showDateTo) {
		String accept = request.getHeader("Accept");
		Outcome outcome = UtilityFactory.createOutcome(OutcomeCode.KO, "Tipo di contenuto della richiesta assente o inatteso");
		ShowsFound showsFound = new ShowsFound();
		showsFound.setShowList(null);
		showsFound.setOutcome(outcome);
		ResponseEntity<ShowsFound> responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.NOT_ACCEPTABLE);

		if (accept != null && accept.contains("application/json")) {
			List<Show> shows = null;
			LocalDate toDate = LocalDate.now().plusWeeks(3);

			try {
				if (!Objects.isNull(showDateTo))
					toDate = LocalDate.parse(showDateTo, formatDateString);

				final LocalDate toLocalDate = toDate;
				if (!Objects.isNull(showDateFrom)) {
					final LocalDate fromLocalDate = LocalDate.parse(showDateFrom, formatDateString);
					shows = this.reportService.readCinemaShows().stream()
																.filter(s -> (s.getTo().isEqual(fromLocalDate) || s.getTo().isAfter(fromLocalDate)) && (s.getTo().isEqual(toLocalDate) || s.getTo().isBefore(toLocalDate)))
																.collect(Collectors.toList());
					showsFound.setShowList(shows);
					showsFound.setOutcome(UtilityFactory.createOutcome(OutcomeCode.OK, "Ricerca terminata con successo"));
					responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.OK);
					log.info(this.getClass().getSimpleName() + " -> getShowsByFromTo #### Ricerca con date: [" + showDateFrom + ", " + toDate.format(formatDateString) + "]. Risultati trovati: " + shows.size());						
				} else {
					// Double check 
					showsFound.setOutcome(UtilityFactory.createOutcome(OutcomeCode.KO, "Inserire la data a partire dal quale ricercare gli spettacoli"));
					responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.INTERNAL_SERVER_ERROR);
					log.debug(this.getClass().getSimpleName() + " -> getShowsByFromTo #### Ricerca non avviata. Data 'from' non specificata");
				}
			} catch (DateTimeParseException pe) {
				log.error("Verificare il formato delle date (Formato yyyy-MM-dd)", pe);
				outcome = UtilityFactory.createOutcome(OutcomeCode.KO, "Verificare il formato delle date (Formato yyyy-MM-dd)");
				showsFound.setOutcome(outcome);
				responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ae) {
				log.error("Couldn't serialize response for content type application/json", ae);
				outcome = UtilityFactory.createOutcome(OutcomeCode.KO, "Errore generico");
				showsFound.setOutcome(outcome);
				responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity<ShowsFound> getOldShows() {
		String accept = request.getHeader("Accept");

		ShowsFound showsFound = new ShowsFound();
		Outcome outcome = UtilityFactory.createOutcome(OutcomeCode.KO, "Tipo di contenuto della richiesta assente o inatteso");
		showsFound.setShowList(null);
		showsFound.setOutcome(outcome);
		ResponseEntity<ShowsFound> responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.NOT_ACCEPTABLE);

		if (accept != null && accept.contains("application/json")) {
			List<Show> shows = null;

			try {
				// Find past shows 
				// ...and distinct them by title (comment if not necessary) 
				shows = this.reportService.readCinemaShows().stream()
															.filter(s -> s.getTo().isBefore(LocalDate.now()))
															.filter(UtilityFactory.distinctByKey(p -> p.getTitle()))
															.collect(Collectors.toList());
				showsFound.setShowList(shows);
				showsFound.setOutcome(UtilityFactory.createOutcome(OutcomeCode.OK, "Ricerca terminata con successo"));
				responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.OK);
				log.info(this.getClass().getSimpleName() + " -> getOldShows #### Risultati trovati: " + shows.size());
			} catch (Exception ae) {
				log.error("Couldn't serialize response for content type application/json", ae);
				showsFound.setOutcome(UtilityFactory.createOutcome(OutcomeCode.KO, "Errore generico"));
				responseEntity = new ResponseEntity<ShowsFound>(showsFound, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return responseEntity;
	}

}
