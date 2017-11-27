package com.example.restapp.flight.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.restapp.flight.dto.FlightDto;
import com.example.restapp.flight.mapper.FlightMapper;
import com.example.restapp.flight.model.Flight;
import com.example.restapp.flight.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	@Qualifier("flightMapper")
	FlightMapper flightMapper;

	/**
	 * @return the flightRepository
	 */
	public FlightRepository getFlightRepository() {
		return flightRepository;
	}

	/**
	 * @param flightRepository
	 *            the flightRepository to set
	 */
	public void setFlightRepository(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	public void persistFlightInformation(final List<FlightDto> pFlightDtoList) {
		// create a new flight information to the in memory database
		final List<Flight> theFlightList = new LinkedList<>();

		// using beanutils to copy all properties from the dto to entity object.
		// saving all the flight information in the database.

		pFlightDtoList.stream().forEach(aFlightDto -> theFlightList.add(createNewFlight(aFlightDto)));
		this.getFlightRepository().saveAll(theFlightList);
	}

	private Flight createNewFlight(final FlightDto pFlightDto) {
		return flightMapper.convertToEntity(pFlightDto);

	}

	@Override
	public List<FlightDto> showAllFlights() {
		Iterable<Flight> theFlightItr = this.getFlightRepository().findAll();
		List<Flight> theFlights = StreamSupport.stream(theFlightItr.spliterator(), false).collect(Collectors.toList());
		return flightMapper.convertToDtos(theFlights);
	}

	/**
	 * @return the flightMapper
	 */
	public FlightMapper getFlightMapper() {
		return flightMapper;
	}

	/**
	 * @param flightMapper
	 *            the flightMapper to set
	 */
	public void setFlightMapper(FlightMapper flightMapper) {
		this.flightMapper = flightMapper;
	}
}
