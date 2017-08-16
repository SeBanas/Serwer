package main;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * One of many classes responsible for as quick response
 * to client from server as fast possible
 *
 */
public class PooledHandler implements Runnable{
	private Socket socket;
	protected static List<Socket> list=new LinkedList<Socket>();
	
	@Override
	public void run() {
		while(true){
			synchronized(list){
				while(list.isEmpty())
					try {
						list.wait();
					} catch (InterruptedException e) {
						ServerInfo.updateError("Request handling problem");
					}
				socket=list.remove(0);
				handleConnection();
			}
		}
	}
		
	/**
	 * Adds socket to socket list awaiting to be handled
	 * @param request Socket from which request has come
	 */
	public static void handleRequest(Socket request){
		if(request!=null)
			synchronized(list){
				list.add(list.size(),request);
				list.notifyAll();
			}	
	}
	
	/**
	 * Handles a connection by getting a request and sending correct
	 * response for it
	 */
	protected void handleConnection(){
		try{
		Connection conn = DriverManager.getConnection(ServerInfo.dataBase, ServerInfo.user,ServerInfo.password);
		ServerProtocol protocol = new ServerProtocol(socket,conn);
			String request=protocol.getRequest();
			protocol.setArguments(request);
			ServerInfo.updateLog("Incoming request "+request);
			protocol.getResponse();
		} catch (SQLException e) {
			ServerInfo.updateError("DataBase connection couldnt be made");
			
		}
	}

}
