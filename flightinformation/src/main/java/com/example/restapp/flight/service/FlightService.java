package com.example.restapp.flight.service;

import java.util.List;

import com.example.restapp.flight.dto.FlightDto;

public interface FlightService {
	public void persistFlightInformation(final List<FlightDto> pFlightDto);
	
	public List<FlightDto> showAllFlights();
}
