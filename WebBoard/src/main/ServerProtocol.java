package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class handling interaction between server and WebBrowsers
 * 
 *
 */
public class ServerProtocol implements Protocols{
	protected ServerConnector serverConnector;
	private StringHandler stringHandler=new ProtocolStringHandler();
	private String arguments;
	private Socket connection;
	private BufferedReader streamReader;
	private PrintWriter streamWriter;
	private BufferedReader fileReader;
	
	/**
	 * Gets reference to socket and connection to DataBase
	 * @param aSocket Socket on which server is listening
	 * @param aConnection Connection with DataBase
	 */
	public ServerProtocol(Socket aSocket,Connection aConnection){
		connection=aSocket;
		serverConnector=new ServerConnector(aConnection);
	}
	
	@Override
	public String getRequest(){
		try {
			streamReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = streamReader.readLine();
			return line;
		} catch (IOException e) {
			ServerInfo.updateError("Error handling request");
			return null;
		}
	};
	
	@Override
	public void getResponse(){
		if(arguments==null) ServerInfo.updateError("Error handling request");
		else try {
			if (arguments.equals("/"))
					arguments="/index.html";
			if (arguments.equals("/index.html"))
				arguments="/index.html?names=*";
			String file=arguments.split("\\?")[0];
			streamWriter = new PrintWriter(connection.getOutputStream());
			String fileToRead = ServerInfo.getFolder() + file;
			System.out.println(fileToRead);
			fileReader = new BufferedReader(new FileReader(fileToRead));
			streamWriter.println(Protocols.OK);
			streamWriter.println(Protocols.CONTENT);
			streamWriter.println("\r\n");
			String line = null;
			while((line=fileReader.readLine())!=null){
				line=stringHandler.processLine(line);
				streamWriter.println(line);
			}
			streamWriter.flush();
			
			} catch (FileNotFoundException e) {
				ServerInfo.updateError("Could not find requested file on the server");
			} catch (IOException e) {
				ServerInfo.updateError("Error handling client");
			}
		finally{
			try{
				if(streamReader!=null)
					streamReader.close();
			}catch(IOException e){}
			try{	
				if(fileReader!=null)
					fileReader.close();
			}catch(IOException e){}
			if(streamWriter!=null)
				streamWriter.close();
		}
	}	
	
	/**
	 * 
	 * @param request Request to be split for arguments. It splits
	 * request by " ".
	 */
	public void setArguments(String request){
		arguments=request.split(" ")[1];
	}
		
	private class ProtocolStringHandler implements StringHandler{

		@Override
		public String processLine(String line) {
			Pattern pattern=Pattern.compile("<SDB>(.*)</SDB>");
			Matcher matcher=pattern.matcher(line);
			if (matcher.find( )) 
		         return processCode(matcher.group(1));
		      else 
		         return line;
		}
		
		@Override
		public String processCode(String code) throws NullPointerException{
				String[] values=arguments.split("\\?");
				int length=values.length;
				if(length<2) return Protocols.ARGUMENTS;
				Pattern pattern;
				Matcher matcher;
				switch(code){
				case Protocols.RECEIVEBYNAME:
					pattern=Pattern.compile("names=([^&^$]*)");
					matcher=pattern.matcher(arguments);
					if (matcher.find( )) 
						return "<p>"+processList(serverConnector.receiveDataByName(matcher.group(1)))+"<p>";
					else return Protocols.ARGUMENTS;
				case Protocols.RECEIVEBYDATE:
					pattern=Pattern.compile("date1=([^&^$]*)&.*date2=([^&^$]*)");
					matcher=pattern.matcher(arguments);
					if (matcher.find( )) 
						return "<p>"+processList(serverConnector.receiveDataByDate(matcher.group(1),matcher.group(2)))+"<p>";
					else return "<p>Insufficient arguments</p>";
				case Protocols.SEND:
					pattern=Pattern.compile("name=(.*)&.*message=(.*)[&$]");
					matcher=pattern.matcher(arguments);
					if (matcher.find( )) 
						return "<p>"+serverConnector.sendData(matcher.group(1),matcher.group(2))+"</p>";
					else return Protocols.ARGUMENTS;
				case Protocols.DELETE:
					pattern=Pattern.compile("names=([^&^$]*)");
					matcher=pattern.matcher(arguments);
					if (matcher.find( )) 
						return "<p>"+serverConnector.deleteByName(matcher.group(1))+"<p>";
					else return Protocols.ARGUMENTS;
				default: return Protocols.CODING;											
				}
		}
		
		private String processList(List<String> list){
			if(list==null) return "<p>No match found</p>";
			StringBuilder stringBuilder=new StringBuilder();
			while(!list.isEmpty())
				stringBuilder.append(list.remove(0)+" <br>");
			return stringBuilder.toString();
		}
		
	}

}
