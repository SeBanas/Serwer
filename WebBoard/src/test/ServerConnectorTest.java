package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;
import main.ServerConnector;

public class ServerConnectorTest {

	@Test
	public void testSendData() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			ServerConnector test=new ServerConnector(conn);
			test.sendData("Guest", "Hello");
			test.sendData(null, null);
		} catch (SQLException e) {e.printStackTrace();}
		
		
		
	}

	@Test
	public void testReceiveDataByName() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			ServerConnector test=new ServerConnector(conn);
			test.receiveDataByName("Guest");
			test.receiveDataByName(null);
		} catch (SQLException e) {e.printStackTrace();}
	}

	@Test
	public void testReceiveDataByDate() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			ServerConnector test=new ServerConnector(conn);
			test.sendData("26.11.2016","21,11,2016" );
			test.sendData(null, null);
			test.sendData("26.2521.2016","21,21311,2016" );
		} catch (SQLException e) {e.printStackTrace();}
	}

}
