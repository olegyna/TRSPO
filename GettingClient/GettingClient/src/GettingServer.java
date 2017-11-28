/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab-2
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class GettingServer extends Thread {
    private ServerSocket serverSocket;
    public List<Socket> clients=new ArrayList<Socket>();
 
   public GettingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
     // serverSocket.setSoTimeout(10000);
   }
   
   

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            clients.add(server);
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            Thread newClient=new Thread(new ClientHandler(server,clients));  
             newClient.start();
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = 7777;
      try {
         Thread t = new GettingServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
    
}
