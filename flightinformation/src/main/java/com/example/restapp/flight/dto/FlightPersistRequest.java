package com.example.restapp.flight.dto;

import java.util.List;

public class FlightPersistRequest {
  private List<FlightDto> flightDtos;
  /**
 * @return the flightDtos
 */
public List<FlightDto> getFlightDtos() {
	return flightDtos;
}
/**
 * @param flightDtos the flightDtos to set
 */
public void setFlightDtos(List<FlightDto> flightDtos) {
	this.flightDtos = flightDtos;
}
/**
 * @return the errorResponse
 */
public String getErrorResponse() {
	return errorResponse;
}
/**
 * @param errorResponse the errorResponse to set
 */
public void setErrorResponse(String errorResponse) {
	this.errorResponse = errorResponse;
}
private String errorResponse;
}
