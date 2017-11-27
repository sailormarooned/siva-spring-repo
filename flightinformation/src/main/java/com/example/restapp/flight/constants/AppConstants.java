package com.example.restapp.flight.constants;

public class AppConstants {
	public static final String VALIDATE_DATE = "(^[1-9]{1}(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[0-9]{2}$|^[0]{1}[1-9]{1}(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[0-9]{2}$|^[1]{1}[0-9]{1}(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[0-9]{2}$|^[2]{1}[0-9]{1}(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[0-9]{2}$|^[3]{1}[0]{1}(JAN|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[0-9]{2}$|^[3]{1}[1]{1}(JAN|MAR|MAY|JUL|AUG|OCT|DEC|jan|mar|may|jul|aug|oct|dec)[0-9]{2}$)";
	public static final String VALIDATE_FLIGHT_NUMBER = "([A-Z0-9]{3,5})";
	public static final String VALIDATE_STATION = "(^[a-zA-Z]{3}$)";
	public static final String VALIDATE_DATETIME = "(^[0-9]{4})";
}
