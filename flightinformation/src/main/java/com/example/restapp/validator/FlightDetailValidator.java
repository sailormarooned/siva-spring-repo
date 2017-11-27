package com.example.restapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.restapp.exception.FlightDataValidationException;
import com.example.restapp.flight.constants.AppConstants;
import com.example.restapp.flight.error.constants.ErrorConstants;

@Component
public class FlightDetailValidator {

	

	
	public boolean validateFlightNumber(final String pFlightNumber) throws FlightDataValidationException {
		Pattern pattern = Pattern.compile(AppConstants.VALIDATE_FLIGHT_NUMBER);
		Matcher matcher = pattern.matcher(pFlightNumber);
		if (!matcher.find()) {
			throw new FlightDataValidationException("Incorrect FlightNumber", ErrorConstants.INCORRECT_FLIGHT_NUMBER);
		}
		
		return true;
	}

	public boolean validateFlightDate(final String pFlightDate) throws FlightDataValidationException {
		
		Pattern pattern = Pattern.compile(AppConstants.VALIDATE_DATE);
		Matcher matcher = pattern.matcher(pFlightDate); 
		if (!matcher.find()) {
			throw new FlightDataValidationException("Incorrect FlightNumber", ErrorConstants.INCORRECT_DATETIME);
		}
		return true;
	}

	public boolean validateStation(final String pStation) throws FlightDataValidationException {
		Pattern pattern = Pattern.compile(AppConstants.VALIDATE_STATION);
		Matcher matcher = pattern.matcher(pStation); 
		if (!matcher.find()) {
			throw new FlightDataValidationException("Incorrect station", ErrorConstants.INCORRECT_STATION);
		}
		return true;
	}

	public boolean validateDateTime(final String pDateTime) throws FlightDataValidationException {
		Pattern pattern = Pattern.compile(AppConstants.VALIDATE_DATETIME);
		Matcher matcher = pattern.matcher(pDateTime); 
		if (!matcher.find()) {
			throw new FlightDataValidationException("Incorrect arrival or departure times",
					ErrorConstants.INCORRECT_DATETIME);
		}
		return true;
	}
}
