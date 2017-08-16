package main;
/**
 * Interface dealing with Strings and replacing 
 * them how the user finds it useful but mostly 
 * used for supporting Streams analysis
 *
 */
public interface StringHandler {
	/**
	 * Checks line for any specified patterns
	 * and handles them in user defined way
	 * @param line Line to be checked
	 * @return Output after operations on line
	 */
	String processLine(String line);
	/**
	 * Deals with given string and returns
	 * a HTML code for placing in document
	 * @param code Specified server function
	 * @return HTML result of processed code
	 */
	String processCode(String code);
	
}
