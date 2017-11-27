package com.example.restapp.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.restapp.flight.dto.FlightDto;
import com.example.restapp.flight.dto.FlightPersistRequest;
import com.example.restapp.flight.parser.FlightDetailsParser;
import com.example.restapp.flight.service.FlightService;

@RestController
public class FileUploadController {

	@Autowired
	private FlightDetailsParser flightDetailParser;

	@Autowired
	private FlightService flightService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam(value = "file", required = true) MultipartFile file) {

		String aResponse = null;
		// read the uploaded flight information file.
		if (!file.getName().endsWith(".csv")) {
			// throw error if the file type does not end with .csv
			aResponse = "File should be of type .csv";
		}
		FlightPersistRequest aFlightPersistRequest = this.getFlightDetailParser().saveUploadedFile(file);

		// save the flight details to in memory database.
		final List<FlightDto> theFlightDtos = aFlightPersistRequest.getFlightDtos();
		flightService.persistFlightInformation(theFlightDtos);

		if (aFlightPersistRequest.getErrorResponse() != null
				&& aFlightPersistRequest.getErrorResponse().trim().length() > 0) {
			aResponse = aFlightPersistRequest.getErrorResponse();
		} else {
			aResponse = "File has been uploaded sucessfully";
		}
		return aResponse;
	}

	@GetMapping("testapplication")

	public String getRequest() {
		return "testing rest webserives";

	}

	/**
	 * @return the flightDetailParser
	 */
	public FlightDetailsParser getFlightDetailParser() {
		return flightDetailParser;
	}

	/**
	 * @param flightDetailParser
	 *            the flightDetailParser to set
	 */
	public void setFlightDetailParser(FlightDetailsParser flightDetailParser) {
		this.flightDetailParser = flightDetailParser;
	}
}
