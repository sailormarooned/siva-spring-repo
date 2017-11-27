package com.example.restapp.flight.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.restapp.exception.FlightDataValidationException;
import com.example.restapp.flight.dto.FlightDto;
import com.example.restapp.flight.dto.FlightPersistRequest;
import com.example.restapp.flight.error.constants.ErrorConstants;
import com.example.restapp.validator.FlightDetailValidator;

@Component
public class FlightDetailsParser {

	@Autowired
	private FlightDetailValidator flightDetailValidator;

	// save file to in memory database.
	//basically this function parses the mutli part file assumed to be of format csv.
	//validation of various elements are done and in case of error an FlightDataValidationException is created,
	//collected and set in the theDataValidationExceptions list. 
	public FlightPersistRequest saveUploadedFile(final MultipartFile file) {
		byte[] bytes = null;

		List<FlightDto> theFlightDetails = new LinkedList<>();
		List<FlightDataValidationException> theDataValidationExceptions = new ArrayList<>();

		FlightPersistRequest aFlightPersistRequest = new FlightPersistRequest();
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			theDataValidationExceptions.add(new FlightDataValidationException(e));
		}

		BufferedReader aBufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
		List<String> theLines = aBufferedReader.lines().collect(Collectors.toList());

		for (String aLine : theLines) {
			try {
				this.parseLine(aLine, theFlightDetails);
			} catch (FlightDataValidationException aDataValidationException) {

				String aErrorMessage = aLine.concat(":").concat(aDataValidationException.getCodeMessage());
				aDataValidationException.setCodeMessage(aErrorMessage);
				theDataValidationExceptions.add(aDataValidationException);
			}
		}

		if (!theDataValidationExceptions.isEmpty()) {
			final String errorResponses = this.buildErrorResponses(theDataValidationExceptions);
			aFlightPersistRequest.setErrorResponse(errorResponses);
		}

		aFlightPersistRequest.setFlightDtos(theFlightDetails);
		return aFlightPersistRequest;
	}

	private void parseLine(final String aLine, final List<FlightDto> theFlightDetails)
			throws FlightDataValidationException {

		// the metadata information of the flight is supposed to be of the following
		// format.
		// flightnumber ,flightdate, origin,destination, daysofoperation,
		// arrivaltime ,departuretime

		String[] theElements = aLine.split(",");
		// incorrect number of elements

		if (theElements.length != 6) {
			final StringBuffer errorText = new StringBuffer(100);

			errorText.append("Incomplete or incorrect Flight information");
			throw new FlightDataValidationException(errorText.toString(), ErrorConstants.INCOMPLETE_FLIGHT_INFORMATION);

		} else {
			FlightDto aFlightDto = new FlightDto();
			// create a new FlightDto object and add it to the list of existing
			// flightDetails array.
			// validate flight number
			if (this.getFlightDetailValidator().validateFlightNumber(theElements[0])) {
				aFlightDto.setFlightNumber(theElements[0]);
			}

			// validate flight date
			if (this.getFlightDetailValidator().validateFlightDate(theElements[1])) {
				aFlightDto.setFlightDate(theElements[1]);
			}

			// validate flight origin
			if (this.getFlightDetailValidator().validateStation(theElements[2])) {
				aFlightDto.setOrigin(theElements[2]);
			}

			// validate flight destination
			if (this.getFlightDetailValidator().validateStation(theElements[3])) {
				aFlightDto.setDestination(theElements[3]);
			}

			// validate flight arrivate time

			if (this.getFlightDetailValidator().validateDateTime(theElements[4])) {
				aFlightDto.setArrivalTime(theElements[4]);
			}

			// validate flight departure time

			if (this.getFlightDetailValidator().validateDateTime(theElements[5])) {
				aFlightDto.setDepartureTime(theElements[5]);
			}

			theFlightDetails.add(aFlightDto);
		}

	}

	private String buildErrorResponses(final List<FlightDataValidationException> pDataValidationExceptions) {
		final StringBuffer aErrorResponse = new StringBuffer(1000);
		pDataValidationExceptions.forEach(aDataValidationException -> aErrorResponse
				.append(aDataValidationException.getCodeMessage()).append("\n"));
		return aErrorResponse.toString();

	}

	/**
	 * @return the flightDetailValidator
	 */
	public FlightDetailValidator getFlightDetailValidator() {
		return flightDetailValidator;
	}

	/**
	 * @param flightDetailValidator
	 *            the flightDetailValidator to set
	 */
	public void setFlightDetailValidator(FlightDetailValidator flightDetailValidator) {
		this.flightDetailValidator = flightDetailValidator;
	}
}
