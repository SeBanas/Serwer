package main;

import java.util.List;

public interface DataBaseConnector {
	/**
	 * Sends specified data to DataBase
	 * and save it to table
	 * @param aPerson Name of author
	 * @param aMassage Text of message
	 * @return Information about action result
	 */
	String sendData(String person,String message);
	/**
	 * Checks DataBase for messages of specified author
	 * @param name Name of author
	 * @return List of matched results
	 */
	List<String> receiveDataByName(String name);
	/**
	 * Checks DataBase for messages between specified
	 * dates
	 * @param dates1 Earlier date
	 * @param dates2 Later date
	 * @return List of matching messages on DataBase
	 */
	List<String> receiveDataByDate(String date1,String date2);

}
