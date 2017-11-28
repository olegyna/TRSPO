
package gettingclient;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;




public class GettingClient {


  public static void main(String [] args) {
      Scanner  sc = new Scanner(System.in);
      String st = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
              " <message>" +
              " <send ot = \"dinkar\" komy = \"kad\"  tekst = \"Hello World!\" />" +
              " </message>");
      String serverName = "127.0.0.1";
      int port = 7777;
      try {


         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         
         out.writeUTF(st);
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
         System.out.println("Server says " + in.readUTF());
         client.close();
      }catch(IOException e) {
         e.printStackTrace();
      }
      }
   }

