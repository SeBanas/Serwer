package main;
/**
 * Interface for classes operating on files includes
 * saving and restoring data from file
 *
 * @param <T> 
 */
public interface FileHandler<T> {
	 /**
	  * Checks file for specified data and returns
	  * them in specified in class constructor form
	  * @param file File to be checked
	  * @return Data gathered in specified form
	  */
	T getFileData(String file);
	/**
	 * Inserts to specified file informations given
	 * by user 
	 * @param input File to be saved
	 */
	void saveToFile(String input);

}
