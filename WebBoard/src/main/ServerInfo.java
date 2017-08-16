package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
/**
 * Class mostly static which gives ability to access
 * Server parameters across whole program. Extending class
 * should implement method which changes current parameters.
 *
 */
public abstract class ServerInfo {
	protected static String mainFolder;
	protected static int port;
	protected static int maxConnections;
	protected static String dataBase;
	protected static String user;
	protected static String password;
	protected static JTextArea logPanel;
	protected static JTextArea errorPanel;
	protected static Date date;
	protected static boolean running;
	
	static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	/**
	 * Gathers informations about server properties and stores
	 * them in static fields 
	 */
	public abstract void updateInfo();
	
	/**
	 * Updates TextArea responsible for showing logs
	 * reported during server lifetime
	 * @param log Log to be displayed
	 */
	public static synchronized void updateLog(String log){
		if(logPanel!=null){
			date=new Date();
			logPanel.setText(logPanel.getText()+dateFormat.format(date)+" "+log+"\n");
		}
	}
	/**
	 * Updates textArea responsible for showing errors
	 * reported during server lifetime
	 * @param error Description of Error
	 */
	public static synchronized void updateError(String error){
		if(logPanel!=null){
			date=new Date();
			errorPanel.setText(errorPanel.getText()+dateFormat.format(date)+" "+error+"\n");
		}
	}

	/**
	 * 
	 * @return Path for Server
	 */
	public static String getFolder(){
		return mainFolder;
	}
	
	/**
	 * 
	 * @return Port of Server
	 */
	public static int getPort(){
		return port;
	}
	
	/**
	 * 
	 * @return Max allowed connections of Server 
	 */
	public static int getMaxConnections(){
		return maxConnections;
	}
	
	/**
	 * 
	 * @return Path of Server DataBase
	 */
	public static String getDataBase(){
		return dataBase;
	}
	
	/**
	 * 
	 * @return User of Server DataBase
	 */
	public static String getUser(){
		return user;
	}
	
	/**
	 * 
	 * @return Password of Server DataBase
	 */
	public static String getPassword(){
		return password;
	}
	
}
