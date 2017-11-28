/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab-2
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.File;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class GettingServer extends Thread {
    private ServerSocket serverSocket;

 
   public GettingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
     // serverSocket.setSoTimeout(10000);
   }

   public void run() {
      while(true) {
         try {
            String otvet = ("1");
            DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            String s = in.readUTF();
            System.out.println (s);
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            InputSource is = new InputSource ();
            is.setCharacterStream(new StringReader(s));
            Document doc;
            doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("send");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);

               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  otvet = ("ot = "+ eElement.getAttribute("ot")+" komy = "+ eElement.getAttribute("komy")+ " tekst = "+ eElement.getAttribute("tekst"));
               }
            }

            out.writeUTF(otvet +"\n");
            server.close();
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         } catch (ParserConfigurationException e) {
            e.printStackTrace();
         } catch (SAXException e) {
            e.printStackTrace();
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
