package com.nagarro.training.constants;

public class Constants {
	// CSV FILES Path
	public static final String FILES_PATH = "/csv/";

	// HQL Query for TShirts
	public static final String OUTPUT_BY_PRICE_QUERY = "FROM TShirt WHERE color=:c AND size=:s AND gender=:g ORDER BY price ASC";
	public static final String OUTPUT_BY_RATING_QUERY = "FROM TShirt WHERE color=:c AND size=:s AND gender=:g ORDER BY rating DESC";
	public static final String OUTPUT_BY_PRICE_AND_RATING_QUERY = "FROM TShirt WHERE color=:c AND size=:s AND gender=:g ORDER BY rating DESC, price";
	// HQL Query for User
	public static final String GET_USER_QUERY = "FROM User WHERE username=:u";

	// Session Data Attributes
	public static final String USER_SESSION_ATTRIBUTE = "user";

	// Login Form Attribute
	public static final String USERNAME_FIELD = "username";
	public static final String PASSWORD_FIELD = "password";
	public static final String USERNAME_ERROR_FIELD = "username_error";
	public static final String PASSWORD_ERROR_FIELD = "password_error";

	// Output Preference Options
	public static final String OUTPUT_PREFERENCES_OPTIONS = "Output Preference:\n1. By Price\n2. By Rating\n3. By Price & Rating\n";
	public static final int OUTPUT_BY_PRICE = 1;
	public static final int OUTPUT_BY_RATING = 2;
	public static final int OUTPUT_BY_PRICE_AND_RATING = 3;

	// CSV Column Position
	public static final int COLUMN_ID = 0;
	public static final int COLUMN_NAME = 1;
	public static final int COLUMN_COLOR = 2;
	public static final int COLUMN_GENDER = 3;
	public static final int COLUMN_SIZE = 4;
	public static final int COLUMN_PRICE = 5;
	public static final int COLUMN_RATING = 6;
	public static final int COLUMN_AVAILABILITY = 7;

	// Exceptions
	public static final String UNKNOWN_ERROR = "Some Error Occurred.";
	public static final String INVALID_VALUE = "Invalid Value.";
	public static final String NO_TSHIRT_FOUND = "No TShirt Found!";
	public static final String CSV_FILE_READ_EXCEPTION = "Error Reading CSV File.";
	public static final String THREAD_ERROR = "Thread Intrruption Error.";
	public static final String NO_FILE_FOUND = "No File Found!";
	public static final String FIELD_CANNOT_BE_EMPTY = "This field cannot be empty.";
	public static final String TSHIRT_ALREADY_EXISTS = "TShirt Already Exists in Database!";
	public static final String USERNAME_DOES_NOT_EXISTS = "Username does not exists.";
	public static final String INCORRECT_PASSWORD = "Incorrect Password.";

}
