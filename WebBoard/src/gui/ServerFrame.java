package gui;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.DigitJTextField;
import main.FileHandler;
import main.ServerBoard;
import main.ServerInfo;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ServerFrame {

	private JFrame frame;
	private Thread serverThread;
	private boolean isRunning=false;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private FileHandler<String[]> fileHandler=new FrameFileHandler();
	private ServerInfo serverInfo=new FrameServerInfo();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame window = new ServerFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
	
		JLabel lblServerConfig = new JLabel("Server config");
		
		JLabel lblMainFolder = new JLabel("Main folder");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblDatabaseConfig = new JLabel("Database config");
		
		JLabel lblDatabasePath = new JLabel("Database path");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMainFolder)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblServerConfig, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
							.addComponent(lblDatabaseConfig)
							.addGap(84))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
							.addComponent(lblDatabasePath)
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(44))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblServerConfig)
						.addComponent(lblDatabaseConfig))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMainFolder)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatabasePath)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Port Number");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		textField = new DigitJTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textField);
		textField.setColumns(4);
		
		JLabel lblNewLabel_2 = new JLabel("Max Connnections");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		textField_1 = new DigitJTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textField_1);
		textField_1.setColumns(4);
		
		JButton btnStart = new JButton("Start");
		panel_1.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isRunning){
					checkParameters();
					btnStart.setText("Stop");
					textField.setEditable(false);
					textField_1.setEditable(false);
					textField_2.setEditable(false);
					textField_3.setEditable(false);
					textField_4.setEditable(false);
					textField_5.setEditable(false);
					isRunning=true;
					checkParameters();
					serverInfo.updateInfo();
					ServerInfo.updateLog("Server starting on port "+ServerInfo.getPort());
					serverThread=new Thread(ServerBoard.CreateServer());
					fileHandler.saveToFile("config.txt");
					serverThread.start();
				}
				else{
					ServerInfo.updateLog("Server closed");
					btnStart.setText("Start");
					textField.setEditable(true);
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					textField_3.setEditable(true);
					textField_4.setEditable(true);
					textField_5.setEditable(true);
					isRunning=false;
					ServerBoard.CloseServer();
				}
				
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUser = new JLabel("User");
		panel.add(lblUser);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_4);
		textField_4.setColumns(9);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_5);
		textField_5.setColumns(9);
		frame.getContentPane().setLayout(groupLayout);
		
		String[] tmp=fileHandler.getFileData("config.txt");
		textField.setText(tmp[0]);
		textField_1.setText(tmp[1]);
		textField_2.setText(tmp[2]);
		textField_3.setText(tmp[3]);
		textField_4.setText(tmp[4]);
		textField_5.setText(tmp[5]);
		
		
	}
	
	public void turnOff(){
		
	}
	
	private void checkParameters(){
		try{
			if(Integer.parseInt(textField.getText())==0)
				textField.setText("666");
		}catch(NumberFormatException e){
			textField.setText("666");
		}
		try{
			if(Integer.parseInt(textField_1.getText())==0)
				textField_1.setText("50");
		}catch(NumberFormatException e){
			textField_1.setText("50");
		}	
	}
	
	private class FrameFileHandler implements FileHandler<String[]>{	
		
		@Override
		public String[] getFileData(String file) {
			try{
			    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			    String line;
			    String[] tab={"","","","","",""};
			    int i=0;
			    while((line=bufferedReader.readLine())!=null){
			    	tab[i]=line;
			    	i++;
			    }
			    bufferedReader.close();
			    return tab;
			} catch (IOException e) {
				ServerInfo.updateError("File config.txt cannot be found");
			   return null;
			}
		}

		@Override
		public void saveToFile(String input) {
			try{
			    PrintWriter writer = new PrintWriter(input, "UTF-8");
			    writer.println(textField.getText());
			    writer.println(textField_1.getText());
			    writer.println(textField_2.getText());
			    writer.println(textField_3.getText());
			    writer.println(textField_4.getText());
			    writer.println(textField_5.getText());
			    writer.close();
			} catch (IOException e) {
			   ServerInfo.updateError("File config.txt cannot be created");
			}
		}
	}

	protected class FrameServerInfo extends ServerInfo{

		@Override
		public void updateInfo() {
			port=Integer.parseInt(textField.getText());
			maxConnections=Integer.parseInt(textField_1.getText());
			mainFolder=textField_2.getText();
			dataBase=textField_3.getText();
			user=textField_4.getText();
			password=textField_5.getText();
			logPanel=textArea;
			errorPanel=textArea_1;
		}
		
	}
}

