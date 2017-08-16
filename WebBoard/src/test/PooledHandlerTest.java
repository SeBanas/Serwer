package test;

import java.net.Socket;
import org.junit.Test;
import main.PooledHandler;

public class PooledHandlerTest {
	
	@Test
	public void testRun() {
		Thread thread = new Thread(new PooledHandler());
		PooledHandlerTest tester=new PooledHandlerTest();
		tester.testHandleRequest();
		thread.start();
		
	}

	@Test
	public void testHandleRequest() {
		Socket socket1=null;
		Socket socket2=new Socket();
		PooledHandler.handleRequest(socket1);
		PooledHandler.handleRequest(socket2);
	}

}
