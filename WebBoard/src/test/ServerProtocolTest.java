package test;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;

import main.ServerProtocol;

public class ServerProtocolTest {

	ServerProtocol makeOne(){
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			Socket socket=new Socket();
			return new ServerProtocol(socket,conn);
		}catch (SQLException e) {e.printStackTrace();return null;}
		
	}
	@Test
	public void testGetRequest() {
		ServerProtocol sp=makeOne();
		sp.getRequest();
	}

	@Test
	public void testGetResponse() {
		ServerProtocol sp=makeOne();
		sp.getResponse();
	}

}
