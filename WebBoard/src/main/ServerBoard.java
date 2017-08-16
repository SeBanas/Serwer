package main;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Top class of server responsible for creating handlers
 * and listening for incoming requests
 *
 */
public class ServerBoard implements Runnable{
	
	protected static ServerSocket serverSocket;
	public volatile static  boolean state=false;	
	
	/**
	 * Creates server based on informations stored in 
	 * ServerInfro class
	 * @return Reference to new Server 
	 */
	public static ServerBoard CreateServer(){
		return new ServerBoard(ServerInfo.getPort(),ServerInfo.getMaxConnections());
	}
	
	/**
	 * Closes server by changing its state and closing socket
	 */
	public static void CloseServer(){
		state=false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			ServerInfo.updateError("Server is already closed");
		}
	}
	
	@Override
	public void run() {
		createPools();
		while(state){
			try {
				PooledHandler.handleRequest(serverSocket.accept());
			} catch (IOException e) {
				ServerInfo.updateError("Server did not respond propely");
			}
		}
		
	}
	
	/**
	 * Creates handlers to be prepared for responding to
	 * request on server. By default number of handlers equals
	 * number of MaxConnections of server
	 */
	protected void createPools(){
		for(int i=0;i<=ServerInfo.getMaxConnections();i++)
				new Thread(new PooledHandler(),"Handler "+i).start();
		}
	
	private ServerBoard(int aPort,int aMax){
		try {
			serverSocket=new ServerSocket(aPort,aMax);
			state=true;
		} catch (IOException e) {
			ServerInfo.updateError("Server cannot be created: Try to change port");
			ServerBoard.CloseServer();
		}
	}
	
}
