package main;
/**
 * Interface containing useful Strings for checking
 * and writing to server processes
 *
 */
public interface Protocols {
	static final String PATTERN="<SDB>(.*)</SDB>";
	static final String SEND="sendData()";
	static final String DELETE="deleteByName()";
	static final String RECEIVEBYNAME="receiveDataByName()";
	static final String RECEIVEBYDATE="receiveDataByDate()";
	static final String ARGUMENTS="<p>Insufficient arguments</p>";
	static final String CODING="<p>Bad coding</p>";
	static final String OK="HTTP/1.1 200 OK";
	static final String ERROR="HTTP/1.1 404 Error";
	static final String CONTENT="Content-Type: text/html";
	
	/**
	 * Gets Socket InputStream and returns its first line
	 * which in browsers contains valuable informations
	 * @return Request send by browser
	 */
	String getRequest();
	
	/**
	 * Handles with request writing to a Stream from specific file
	 * and sending it back to browser after operations done on it
	 */
	void getResponse();
	

}
