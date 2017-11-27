package com.example.restapp.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restapp.flight.service.FlightService;

@Controller
public class FlightMetadataController {

	@Autowired
	private FlightService flightService;

	@RequestMapping("/archive/fileupload")
	public String uploadFile(Model model) {
		model.addAttribute("message", "upload a new file");
		return "fileupload";
	}

	@RequestMapping("/archive/show")
	public String showFlightDetails(Model model) {
		model.addAttribute("flights", flightService.showAllFlights());
		return "flightinformation";
	}

	/**
	 * @return the flightService
	 */
	public FlightService getFlightService() {
		return flightService;
	}

	/**
	 * @param flightService
	 *            the flightService to set
	 */
	public void setFlightService(FlightService flightService) {
		this.flightService = flightService;
	}
}
