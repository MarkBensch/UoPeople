package edu.uopeople.cs1103.unit7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This application is a HTTP web server that will handle GET requests
 * @author Mark Bensch
 */

public class AssignmentSeven {
	static final int LISTENING_PORT = 60606; //port
	static final String ROOT_LOCATION = System.getProperty("user.dir") + "/resources/www"; //root location execution location

	public static void main( String[] args ) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket( LISTENING_PORT );
		} catch ( Exception e ) {
			System.out.println( "Failed to create listening socket." );
			return;
		}
		System.out.println( "Listening on port " + LISTENING_PORT );
		try {
			while ( true ) {
				ConnectionThread thread = new ConnectionThread(serverSocket.accept());
				thread.start();
			}
		} catch ( Exception e ) {
			System.out.println( "Server socket shut down unexpectedly!" );
			System.out.println( "Error: " + e );
			System.out.println( "Exiting." );
		}
	}

	/**
	 * the main class that will handle the connection and fetch files for the connecting client (Browser)
	 * @param connection Socket connection
	 */
	private static void handleConnection( Socket connection ) {
		try {

			Scanner in = new Scanner(connection.getInputStream());
			String getRequestFile = requestMethodGet( in.nextLine() )[1];
			File send = getFile(ROOT_LOCATION + getRequestFile);
			System.out.println(ROOT_LOCATION + getRequestFile);
			sendFile(send, connection.getOutputStream());

		} catch ( Exception e ) { //take the errors thrown and split it for parts
			String[] message = e.getMessage().split(":");
			try {
				sendErrorResponse(Integer.parseInt( message[0] ), message[1], connection.getOutputStream());
			} catch ( IOException e1 ) { // if something goes wrong just print the stack and jump to finally
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close(); // no matter what close the connection
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * this is a method that take the File and converts it into bytes and sends it over the output stream
	 * @param file File to send over the Socket
	 * @param socketOut outputStream of the Socket
	 * @throws IOException
	 * @author UofPeople
	 */
	private static void sendFile(File file, OutputStream socketOut) throws
			IOException {
		sendheader(file, socketOut);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(socketOut);
		while (true) {
			int x = in.read(); // read one byte from file
			if (x < 0)
				break; // end of file reached
			out.write(x);  // write the byte to the socket
		}
		out.flush();
	}

	/**
	 * create a Header for a successful HTTP request
	 * @param file File to get name and Length
	 * @param socketOut outputStream to push the HTTP header down the stream
	 */
	private static void sendheader(File file, OutputStream socketOut) {
		PrintWriter out = new PrintWriter( socketOut );
		String status = "HTTP/1.1 "+ statusCode(200);
		String connection = "Connection: close";
		String contentType = "Content-Type: " + getMimeType(file.getName());
		String contentLength = "Content-Length: " + file.length();
		out.print(status + "\r\n");
		out.print(connection + "\r\n");
		out.print(contentType + "\r\n");
		out.print(contentLength + "\r\n");
		out.print("\r\n");
		out.flush();
	}

	/**
	 * Pass the MimeType back by parsing the file extension of a file name
	 * @param fileName select MimeType by file extension
	 * @return a String with correct MimeType
	 * @author UoPeople
	 * @author MJB updated If to a Switch
	 */
	private static String getMimeType(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) {  // no file extension in name
			return "x-application/x-unknown";
		}
		switch(fileName.substring(pos+1).toLowerCase()){
			case("txt"): return "text/plain";
			case("html"): return "text/html";
			case("css"): return "text/css";
			case("js"): return "text/javascript";
			case("java"): return "text/x-java";
			case("jpeg"): return "image/jpeg";
			case("jpg"): return "image/jpeg";
			case("htm"): return "text/html";
			case("png"): return "image/png";
			case("gif"): return "image/gif";
			case("ico"): return "image/x-icon";
			case("class"): return "application/java-vm";
			case("jar"): return "application/java-archive";
			case("zip"): return "application/zip";
			case("xml"): return "application/xml";
			case("xhtml"): return"application/xhtml+xml";
			default: return "x-application/x-unknown";
		}
		// Note:  x-application/x-unknown  is something made up;
		// it will probably make the browser offer to save the file.
	}

	/**
	 * Get or Create a file from a string path.
	 * if the file is a directory create a index.txt. and serve that
	 * @param location String of a path to the file.
	 * @return A file to send over the socket
	 * @throws Exception differing exceptions will be thrown.
	 */
	private static File getFile(String location) throws Exception{
		File file = new File(location);
		System.out.println("Serving File: " + file.getName());

		if(file.isDirectory()) {  // if the file is a directory make a index.txt file and serve that. //probably should just make a temp through a stream like error
			String defaultFileName = location + "index.txt";
			FileWriter fileWriter = new FileWriter( defaultFileName );
			PrintWriter printWriter = new PrintWriter( fileWriter );
			printWriter.print( "file did not exist so a index file was created" );
			printWriter.flush();
			printWriter.close();
			return getFile( defaultFileName ); //pass the new file back into the method to verify everything again
		}else if(!file.exists()){
			throw new FileNotFoundException("404: unable to locate file");
		} else if(!file.canRead()){
			throw new IOException("403: File is open and locked by another program");
		} else if(file.length()<=0){
			throw new IOException( "404: File has no content." );
		} else {
			return file;
		}
	}

	/**
	 * Send an http error to the socket
	 *
	 * @param errorCode integer HTTP error code
	 * @param message String to explain the error code
	 * @param socketOut the stream to send the information out of.
	 * @throws IOException
	 */
	static void sendErrorResponse(int errorCode, String message, OutputStream socketOut) throws IOException {
		PrintWriter out = new PrintWriter( socketOut );
		String status = "HTTP/1.1 " + statusCode(errorCode);
		String connection = "Connection: close";
		String contentType = "Content-Type: " + getMimeType("error.html");
		out.print(status + "\r\n");
		out.print(connection + "\r\n");
		out.print(contentType + "\r\n");
		out.print("\r\n");
		out.flush();

		String Body = "<html><head><title>Error</title></head><body>\n" +
				"<h2>Error: "+ statusCode(errorCode) +"</h2>\n" +
				"<p>"+ message +"</p>\n" +
				"</body></html>";
		out.println(Body);
		out.flush();
	}

	/**
	 * designed to return a status code string from a integer code
	 * @param errorCode integer of the HTTP status code
	 * @return a string with the proper status header
	 */
	private static String statusCode( int errorCode ) {
		switch(errorCode){
			case(200):
				return "200 OK";
			case(404):
				return "404 Not Found";
			case(403):
				return "403 Forbidden";
			case(400):
				return "400 Bad Request";
			case(501):
				return "501 Not Implemented";
			case(500):
				return "500 Internal Server Error";
			default:
				return "500 Internal Server Error";
		}
	}

	/**
	 * This method parses the string looking for a specific pattern
	 * GET / HTTP/1.1
	 * @param line a string that should contain the Method / directory / protocol
	 * @return a string array from the first header line of a browser request
	 * @throws Exception Several exceptions will be thrown with the first part being the http error code
	 */
	private static String[] requestMethodGet( String line ) throws Exception {
		if(line.startsWith( "GET" )) { // does the line start with GET
			String[] method = line.split( " " );
			if ( method.length == 3 ) { // are there the 3 sections we expect
				if( method[0].equals("GET") && method[1].startsWith("/") && method[2].startsWith( "HTTP/1." )){  // do the three sections have what we expect?
					return method;
				} else {
					throw new IllegalStateException( "400: Invalid Method Header" + method.toString()); // does not have the line we expect
				}
			} else {
				throw new Exception( "400: invalid method Length" ); // does not have 3 sections
			}
		} else {
			throw new IllegalStateException("501: Invalid Method Please use GET:\n " + line); // line does not start with GET
		}
	}

	/**
	 * create a new thread for a Socket Connection
	 * @author UofPeople
	 */
	private static class ConnectionThread extends Thread {
		Socket connection;
		ConnectionThread(Socket connection) {
			this.connection = connection;
		}
		public void run() {
			System.out.println( "\nConnection from "
					+ connection.getRemoteSocketAddress() );
			handleConnection(connection);
		}
	}
}
