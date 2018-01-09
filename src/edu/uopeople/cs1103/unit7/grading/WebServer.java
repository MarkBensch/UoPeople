package edu.uopeople.cs1103.unit7.grading;
/*
 * CS1103
 * Written Assignment: Unit 7s
 * "A Web Server"
 * Author: Michael Pastor
 * Created: 2017.08.02
 * 
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class WebServer {

	private final static int LISTENING_PORT = 50505;
	static Socket client;
	static DataInputStream in;
	static PrintStream out;
	static String myFile;
	//static File file;
	

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        }
        catch (Exception e) {
            System.out.println("Failed to create listening socket.");
            System.out.println(e);
            return;
        }
        System.out.println("Listening on port " + LISTENING_PORT);
        try {
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("\nConnection from "
                        + connection.getRemoteSocketAddress());
                ConnectionThread connThread = new ConnectionThread(connection);
                connThread.start();
                //handleConnection(connection);
            }
        }
        catch (Exception e) {
            System.out.println("Server socket shut down unexpectedly!");
            System.out.println("Error: " + e);
            System.out.println("Exiting.");
        }
    }
    
    private static void handleConnection(Socket connection)  {
    	String rootDir = System.getProperty("user.dir") + "/resources/www";
    	client = connection;
    	
		try {
			 in = new DataInputStream(client.getInputStream());
			 out = new PrintStream(client.getOutputStream());
			 
			 String line = null;
			 String req = null;
			 
			 req = in.readLine();
			 
			 line = req;
			 while(line.length()>0){
				 line = in.readLine();
			 }
			 
			 StringTokenizer strTok = new StringTokenizer(req);
			 
			 if(! strTok.nextToken().equals("GET")){
				 sendErrorResponse(501);
				 return;
			 }
			 
			 myFile = strTok.nextToken();
			 System.out.println(rootDir + myFile);
			 
			 File fMyFile = new File (rootDir + myFile);
			 if (!fMyFile.exists()){
				 sendErrorResponse(404);
				 return;
			 }

			 sendResponseHeader(getMimeType(myFile),(int) fMyFile.length());

			 
			 sendFile(fMyFile,client.getOutputStream());
			 
		}
		
		
		catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
		}


/*		try{
			PrintWriter out = new PrintWriter(connection.getOutputStream());
//			PrintWriter out = new PrintWriter(System.out);
			out.println("HTTP/1.1 200 OK");
			out.println("Connection: close");
			out.println("Content-Type: "+ getMimeType(file.getName()));
			out.println("Content-Length: " + file.length());
			out.flush();
			
//			sendFile(file, connection.getOutputStream());
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}*/
		finally {  
			try {
				connection.close();
			}
			catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}

	private static String getMimeType(String fileName) {
	            int pos = fileName.lastIndexOf('.');
	            if (pos < 0)  // no file extension in name
	                return "x-application/x-unknown";
	            String ext = fileName.substring(pos+1).toLowerCase();
	            if (ext.equals("txt")) return "text/plain";
	            else if (ext.equals("html")) return "text/html";
	            else if (ext.equals("htm")) return "text/html";
	            else if (ext.equals("css")) return "text/css";
	            else if (ext.equals("js")) return "text/javascript";
	            else if (ext.equals("java")) return "text/x-java";
	            else if (ext.equals("jpeg")) return "image/jpeg";
	            else if (ext.equals("jpg")) return "image/jpeg";
	            else if (ext.equals("png")) return "image/png";
	            else if (ext.equals("gif")) return "image/gif";
	            else if (ext.equals("ico")) return "image/x-icon";
	            else if (ext.equals("class")) return "application/java-vm";
	            else if (ext.equals("jar")) return "application/java-archive";
	            else if (ext.equals("zip")) return "application/zip";
	            else if (ext.equals("xml")) return "application/xml";
	            else if (ext.equals("xhtml")) return"application/xhtml+xml";
	            else return "x-application/x-unknown";
	               // Note:  x-application/x-unknown  is something made up;
	               // it will probably make the browser offer to save the file.
	         }
	
	
	private static void sendFile(File file, OutputStream socketOut) throws IOException {
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

	
	private static void sendResponseHeader(String type,int length){
		out.println("HTTP/1.1 200 OK");
		out.println("Content-type: " +type);
		out.println("Content-Length: " +length);
		out.println("Connection: close " + "\r\n");
	}

	 private static void sendErrorResponse(int errorCode){
		 switch(errorCode) {
		 	case 404:
		 		out.print("HTTP/1.1 404 Not Found");
		 		out.println("Connection: close " );
		 		out.println("Content-type: text/plain" +"\r\n");
		 		out.println("<html><head><title>Error</title></head><body> <h2>Error: 404 Not Found</h2> <p>The resource that you requested does not exist on this server.</p> </body></html>");
		 		break;
		 	case 501:
		 		out.print("HTTP/1.1 501 Not Implemented");
		 		out.println("Connection: close " );
		 		out.println("Content-type: text/plain" +"\r\n");
		 		break;
		 	} 
		 }
	 
	 private static class ConnectionThread extends Thread {
		 Socket connection;
		 ConnectionThread(Socket connection) {
			 this.connection = connection;
			 }
		 public void run() {
			 handleConnection(connection);
			 }
		 }

	
	
}









