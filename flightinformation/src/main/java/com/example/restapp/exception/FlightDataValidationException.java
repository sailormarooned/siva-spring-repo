package com.example.restapp.exception;

import java.util.LinkedList;
import java.util.List;

import com.example.restapp.flight.error.constants.ErrorConstants;

public class FlightDataValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8645964816291490673L;

	private String codeMessage;

	private int errorCode;

	private FlightDataValidationException() {
		super();
	}

	public FlightDataValidationException(final String pMessage, final int errorCode) {
		super(pMessage);
		this.setCodeMessage(pMessage);
		this.errorCode = errorCode;
	}

	public FlightDataValidationException(final Throwable pThrowable, final int errorCode) {
		super(pThrowable.getMessage());
		this.setCodeMessage(pThrowable.getMessage());
		this.errorCode = errorCode;
	}

	public FlightDataValidationException(final Throwable pThrowable) {
		super(pThrowable.getMessage());
		this.setCodeMessage(pThrowable.getMessage());
		this.errorCode = ErrorConstants.INTERNAL_ERROR;
	}

	

	/**
	 * @return the codeMessage
	 */
	public String getCodeMessage() {
		return codeMessage;
	}

	/**
	 * @param codeMessage the codeMessage to set
	 */
	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
