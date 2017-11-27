package com.example.restapp.flight.mapper;

import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.restapp.flight.dto.FlightDto;
import com.example.restapp.flight.model.Flight;

@Component("flightMapper")
public class FlightMapper extends ModelMapper {



	public Flight convertToEntity(final FlightDto pFlightDto) {
		Flight aFlight = map(pFlightDto, Flight.class);
		return aFlight;
	}

	public FlightDto convertToDto(final Flight pFlight) {
		FlightDto aFlightDto =map(pFlight, FlightDto.class);
		return aFlightDto;
	}

	public List<FlightDto> convertToDtos(List<Flight> theFlights) {
		List<FlightDto> theFlightDtos=new LinkedList<FlightDto>();
		theFlights.forEach(flight->theFlightDtos.add(convertToDto(flight)));
		return theFlightDtos;
	}
}
