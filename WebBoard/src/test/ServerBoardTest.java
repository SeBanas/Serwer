package test;

import org.junit.Test;
import main.ServerBoard;

public class ServerBoardTest {

	@Test
	public void testCreateServer() {
		@SuppressWarnings("unused")
		ServerBoard serverBoard=ServerBoard.CreateServer();
		ServerBoard.CloseServer();
	}

	@Test
	public void testCloseServer() {
		ServerBoard.CloseServer();
	}

	@Test
	public void testRun() {
		Thread thread=new Thread(ServerBoard.CreateServer());
		thread.start();
		this.testCloseServer();
	}

}
