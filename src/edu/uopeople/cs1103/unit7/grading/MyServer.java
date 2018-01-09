package edu.uopeople.cs1103.unit7.grading;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import javax.swing.text.JTextComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

/**
 * @author JavaHome
 *
 */
public class MyServer extends JFrame  {
	/**
	 * The server listens on this port.  Note that the port number must
	 * be greater than 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50505;
	
	private static JTextArea tfServerIn;
	private JTextArea tfServerOut;
	private JTextArea tfServerStatus;
	
	JScrollPane scrollPane;
	JScrollPane scrollPane2;
	
	private ArrayList <String> ssu = new ArrayList<String> ();	//Server status update
	private ArrayList <String> ssi = new ArrayList<String> ();	//Server Incoming messages
	private ArrayList <String> sso = new ArrayList<String> ();	//Server Out Going Messages
	
	private File fileLocation; 
	
	public MyServer() {
		setResizable(false);
		//***********Start***********All of the controls added to the JFrame - Start *******************************************
		setTitle("CS1103 (week 7) -  Server");
		getContentPane().setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 256, 204);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Server : Messages (most recent on top)");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_2, BorderLayout.NORTH);
		
		tfServerStatus = new JTextArea();
		tfServerStatus.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		tfServerStatus.setLineWrap(true);
		tfServerStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(tfServerStatus, BorderLayout.CENTER);
		tfServerStatus.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(274, 11, 380, 204);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Server: Incoming Messages (most recent on top)");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		tfServerIn = new JTextArea();
		tfServerIn.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		//panel.add(tfServerIn, BorderLayout.CENTER);
		tfServerIn.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tfServerIn);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(8, 218, 256, 204);
		getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblServerActions = new JLabel("Server : File Selection ");
		lblServerActions.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerActions.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel_3.add(lblServerActions, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Select File to be Sent! ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 11, 236, 38);
		panel_5.add(lblNewLabel_4);
		
		JButton btnFileSelection = new JButton("File Selection");
		btnFileSelection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFileSelection.setBounds(10, 60, 236, 38);
		panel_5.add(btnFileSelection);
		
		JLabel lblFileSelected = new JLabel("No File Selected");
		lblFileSelected.setOpaque(true);
		lblFileSelected.setBackground(Color.RED);
		lblFileSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileSelected.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFileSelected.setBounds(10, 132, 236, 47);
		panel_5.add(lblFileSelected);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(274, 218, 380, 204);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		tfServerOut = new JTextArea();
		tfServerOut.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		//panel_1.add(tfServerOut, BorderLayout.CENTER);
		tfServerOut.setColumns(10);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(tfServerOut);
		panel_1.add(scrollPane2, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Server : Out Going Messages (most recent on top)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.NORTH);
		
		//***********END***********All of the controls added to the JFrame - END *******************************************
		
		// ************** Actions Performed on Controls - Start ********************
		
		//shutdown the program if the main window is closed
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		//Choose the target file to send
		btnFileSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MediaDirChooser mdc = new MediaDirChooser();
				fileLocation = mdc.getFile();
				serverMessageUpdate(tfServerStatus,ssu, "File Location: " + fileLocation);
				
				//If the file exists or doesn't exist then update the label text and color
				if (fileLocation !=null &&  fileLocation.exists() ) {
					lblFileSelected.setBackground(Color.GREEN);
					lblFileSelected.setText("File Selected");
				} else {
					lblFileSelected.setBackground(Color.RED);
					lblFileSelected.setText("No File Selected");
				}
			}
		});
		// ************** Actions Performed on Controls - END ********************
	}
	
	
	public static void main(String[] args) {
		//instantiate the JFrame
		MyServer ms = new MyServer();
		ms.setResizable(false);
		ms.setLocation(new Point(450,275));
		ms.setMinimumSize(new Dimension(682, 475));

		ms.pack();
		ms.setVisible(true);
		
		//The thread code - engage
		ms.runMe();

	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void runMe() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		}
		catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			//update the Server Status Message area
			serverMessageUpdate(tfServerStatus,ssu, "Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		//update the Server Status Message area
		serverMessageUpdate(tfServerStatus,ssu, "Listening on port " + LISTENING_PORT);		
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				
				System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
				//update the Server Status Message area
				serverMessageUpdate(tfServerStatus,ssu, "Connection incoming: " + connection.getRemoteSocketAddress());	
				//update the Server incoming Message area
				serverMessageUpdate(tfServerIn,ssi, "Connection from " + connection.getRemoteSocketAddress());					
				
				//Create a thread that will handle the Connection issues
				ConnectionThread thread = new ConnectionThread( connection);
			    thread.start();
			    serverMessageUpdate(tfServerStatus,ssu, "Thread Created - " + thread.toString());
			}
		}
		catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			serverMessageUpdate(tfServerStatus,ssu,"Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			serverMessageUpdate(tfServerStatus,ssu, "Error: " + e);
			System.out.println("Exiting.");
			serverMessageUpdate(tfServerStatus,ssu, "Exiting.");
		}
	}
	
	/**
	 * This is the handleConnection that i am supposed to modify and handle any of the networking 
	 * 		errors that may occur.  I did adopt the framework that was given in the assignment but 
	 * 		i did add my own error handling. 
	 * 		-So far this code is very messy and needs to be cleaned up
	 * 
	 * @param connection
	 */
	private void handleConnection(Socket connection) {
		try {
			//My attempt to clean up the code a little
			/*
			 * Handle the Input requests separately
			 */
			String[] firstLine = handleTheInputStream(connection);
			
			String pathToResource = firstLine[1]; 	// path to resource
			String httpVersion = firstLine[2];		// http protocol version

			OutputStream outputStream = connection.getOutputStream();
			if (fileLocation != null)
				pathToResource = fileLocation.toString();

			File file = new File(pathToResource); 
			if (file.exists()) // check if file exists
				if (!file.isDirectory() && file.length() != 0) {// i do not want it to be a directory of have a length of 0
					if (file.canRead()) {
						// System.out.println("Response can be sent.");
						PrintWriter out = new PrintWriter(outputStream);
						out.print(httpVersion + " 200 OK " + "\r\n");
						out.print("Connection:close" + "\r\n");
						out.print("Content-Type:" + getMimeType(pathToResource) + "\r\n");
						out.print("Content-Length:" + file.length() + "\r\n");
						out.print("\r\n");
						out.flush();
						
						//Send a message to the ServerOut textArea
						String message =  	httpVersion + "  200 OK " + "\n" +
											"     Connection:close" + "\n" +
											"     Content-Type:" + getMimeType(pathToResource) + "\n" +
											"     Content-Length:" + file.length() + "\n";
						serverMessageUpdate(tfServerOut,sso, message);
						
						serverMessageUpdate(tfServerOut,sso, "Sending File: File data ......Sending");
						long startTime = System.currentTimeMillis();
						sendFile(file, outputStream); // sends file
						long endTime = System.currentTimeMillis() - startTime;
						serverMessageUpdate(tfServerOut,sso, "Sending File: File data ......Complete");
						serverMessageUpdate(tfServerOut,sso, "Elapsed Time: " + endTime +"ms");
						out.close();
					} else {
						sendErrorResponse(403, outputStream);
						//in.close();
						connection.close();
						return;
					}
				} else {
					sendErrorResponse(404, outputStream);
					//in.close();
					connection.close();
					return;
				}
			//in.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("Error - File Not Found: " + fnfe);
			serverMessageUpdate(tfServerStatus,ssu, "Error - File Not Found: " + fnfe);
		} catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
			serverMessageUpdate(tfServerStatus,ssu, "Error while communicating with client: " + e);
		} finally {  // make SURE connection is closed before returning!
			try {
				connection.close();
			}
			catch (Exception e) {
			}
			System.out.println("Connection closed.");
			serverMessageUpdate(tfServerStatus,ssu, "Connection closed.");
		}
	}
	
	/**This method is the INPUT portion separated to help clean up the code a little.
	 * @param connection, the established connection
	 * @return String[], the GET header information
	 */
	private String[] handleTheInputStream (Socket connection) {
		String theHandleMessage="";
		String[] firstLine = new String[3];
		try {
			Scanner in = new Scanner(connection.getInputStream());
			
			String line="";
			int c = 0;
			while (true) {
				if ( ! in.hasNextLine() )
					break;
				line = in.nextLine();
				c++;
				if (line.trim().length() == 0)
					break;
				 if (c == 1) {
					 firstLine = line.split(" ");
					 //break;
				 }
				System.out.println("   " + line);
				theHandleMessage = theHandleMessage + "   " + line + "\n" ;
			}
			serverMessageUpdate(tfServerIn,ssi, theHandleMessage);
			
			String httpRequestType = firstLine[0]; 	// http request type
			String pathToResource = firstLine[1]; 	// path to resource
			String httpVersion = firstLine[2];		// http protocol version
			
			OutputStream outputStream = connection.getOutputStream();

			if (fileLocation != null)
				pathToResource = fileLocation.toString();

			File file = new File(pathToResource); 

			if (!httpRequestType.equals("GET")) {
				sendErrorResponse(501, outputStream);
				in.close();
				connection.close();
			} else {
				serverMessageUpdate(tfServerOut,sso, "A Valid GET Request Made from: " + connection.getInetAddress().toString());
			}
			if (!httpVersion.equals("HTTP/1.1")) {
				sendErrorResponse(400, outputStream);
				in.close();
				connection.close();
			} else { 
				serverMessageUpdate(tfServerOut,sso, "Valid HTTP version: HTTP/1.1 = " + httpVersion);
			}
			//in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("handleTheInputStream : " + e);
			e.printStackTrace();
		} 
		
		return firstLine;
	}

	/** THis method is used to return mime-type.  This is another method from the 
	 * 		Assignment text and i am using it here.
	 * 
	 * @param fileName
	 * @return
	 */
	private  String getMimeType(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) // no file extension in name
			return "x-application/x-unknown";
		String ext = fileName.substring(pos + 1).toLowerCase();
		if (ext.equals("txt"))
			return "text/plain";
		else if (ext.equals("html"))
			return "text/html";
		else if (ext.equals("htm"))
			return "text/html";
		else if (ext.equals("css"))
			return "text/css";
		else if (ext.equals("js"))
			return "text/javascript";
		else if (ext.equals("java"))
			return "text/x-java";
		else if (ext.equals("jpeg"))
			return "image/jpeg";
		else if (ext.equals("jpg"))
			return "image/jpeg";
		else if (ext.equals("png"))
			return "image/png";
		else if (ext.equals("gif"))
			return "image/gif";
		else if (ext.equals("ico"))
			return "image/x-icon";
		else if (ext.equals("class"))
			return "application/java-vm";
		else if (ext.equals("jar"))
			return "application/java-archive";
		else if (ext.equals("zip"))
			return "application/zip";
		else if (ext.equals("xml"))
			return "application/xml";
		else if (ext.equals("xhtml"))
			return "application/xhtml+xml";
		else
			return "x-application/x-unknown";
		// Note: x-application/x-unknown is something made up;
		// it will probably make the browser offer to save the file.
	}
	/**
	 * This method is used to send the error responses.  The error codes came
	 * 		from the Assignment text and i just copied them here with some structure
	 * 
	 * @param errorCode
	 * @param socketOut
	 */
	private void sendErrorResponse(int errorCode, OutputStream socketOut) {
		PrintWriter out = new PrintWriter(socketOut);
		if (errorCode == 404) {
			out.print("HTTP/1.1" + errorCode + " Not Found " + "\r\n");
			out.print("Connection:close" + "\r\n");
			out.print("Content-Type:" + "text/html" + "\r\n");
			out.print("<html><head><title><b>Error</b></title></head><body>" + "<h2>Error: 404 Not Found</h2>"
					+ "<p>The resource that you requested does " + "not exist on this server.</p></body></html>");
			serverMessageUpdate(tfServerOut,sso, "Error: 404 Not Found.\nThe resource that you requested does not exist on this server.");

		} else if (errorCode == 403) { 
			out.print("HTTP/1.1" + errorCode + " Forbidden - you do not have the permissions to access this file. " + "\r\n");
			out.print("Connection:close" + "\r\n");
			out.print("Content-Type:" + "text/html" + "\r\n");
			serverMessageUpdate(tfServerOut,sso, "Forbidden 403 - you do not have the permissions to access this file.");

		} else if (errorCode == 400) { 
			out.print("HTTP/1.1" + errorCode + " Bad Request -  third token is not HTTP/1.1 or HTTP/1.0" + "\r\n");
			out.print("Connection:close" + "\r\n");
			serverMessageUpdate(tfServerOut,sso, "Bad Request 400 -  third token is not HTTP/1.1 or HTTP/1.0");
		} else if (errorCode == 501) { 
			out.print("HTTP/1.1" + errorCode + " Not Implemented - The request was not a GET" + "\r\n");
			out.print("Connection:close" + "\r\n");
			serverMessageUpdate(tfServerOut,sso, "Not Implemented 501 - The request was not a GET");
		} else if (errorCode == 500) { 
			out.print("HTTP/1.1" + errorCode + " Internal Server Error -  catch some unexpected error encountered" + "\r\n");
			out.print("Connection:close" + "\r\n");
			serverMessageUpdate(tfServerOut,sso, "Internal Server Error 500 -  catch some unexpected error encountered.");
		}
		out.print("\r\n");
		out.flush();
		out.close();
	}
	/**
	 * THis method is used to send a file to the caller (Browser).
	 * 		This was taken from the Assignment text.  
	 *
	 * @param file
	 * @param socketOut
	 * @throws IOException
	 */
	private  void sendFile(File file, OutputStream socketOut) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(socketOut);
		while (true) {
			int x = in.read(); // read one byte from file
			if (x < 0)
				break; // end of file reached
			out.write(x); // write the byte to the socket
		}
		out.flush();
		in.close();
	}
	/**
	 * The purpose of this method is to update a component with new text.
	 * 		-Specifically i am using the JTextArea as it is easy to handle 
	 * 		text formats and multiple rows of data.  
	 * 		-I do cap the message number to 15 by checking the size of the 
	 * 		incoming ArrayList
	 * 		-I also tag every incoming message with a time.  If i do not then 
	 * 		the messages seem to get  meaning less without the time stamp
	 * 		-I synchronized this method in case more than one Thread tries to
	 * 		access the method and i, as a result, get unexpected data
	 * 
	 * @param cmp, this is the component that will be updated with the text data
	 * @param al, This is the ArrayList that contains the previous messages
	 * @param str,  This is the current message to be added
	 */
	private synchronized void serverMessageUpdate(JTextArea cmp, ArrayList<String> al, String str) {
		al.add("("+ ( new SimpleDateFormat("HH:mm:ss").format( new GregorianCalendar().getTime() ) ) +") " + str);

		/*
		 * Checking to see if the ArrayList is > 15.  If it is then i am 
		 * 		going to remove item 0.  I remove item 0 since it is just
		 * 		easy to do and since i add the newest item to the top of
		 * 		the message text.  By removing item 0 this item appears to
		 * 		disappear from the message text area from the bottom.
		 */
		if (al.size() > 15) {
			al.remove(0);
			al.trimToSize();
		}
		String theMessage="";

		for (String item : al) {
			//System.out.println(item);
			/*
			 * Step through the ArrayList to create the component message.  
			 * 		-I add the item before the message body so that i always
			 * 		add the newest item to the top of the message ouput
			 */
			theMessage ="-" + item  + "\n" + theMessage  ;
		}
		//Here i am updating the passed component(JTextArea) with the message
		cmp.setText(theMessage);		
	}


	
	
	 /**
	   *
	   * ConnectionThread class
	   *
	   */
	class ConnectionThread extends Thread {
		Socket connection;
		
		ConnectionThread(Socket connection) {
			this.connection = connection;		
		}

		public void run() {
			handleConnection(connection);
		}
	}
}
