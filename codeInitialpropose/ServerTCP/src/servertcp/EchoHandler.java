package servertcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


class EchoHandler extends Thread {
  Socket client;
  private InfoClients ic=null;
  private GestionListeClients listeClient;
  EchoHandler (Socket client,GestionListeClients listeClient) {
    this.client = client;
      try {
          ic = new InfoClients(client);
          this.listeClient=listeClient;
          listeClient.add(ic);
      } catch (IOException ex) {
          Logger.getLogger(EchoHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  @Override
  public void run () {
      BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
        String line;
       
        while (!(line=reader.readLine()).equals(".")) {
          //writer.println("je reponds " + line);
            System.out.printf("J'ai recus %s\n", line);
          listeClient.sendMessageToAll(line);
        }
        writer.println("bye");
    }
    catch (Exception e) {
      System.out.println("ERROR"+e);
      System.err.println("Exception caught: client disconnected.");
    }
    finally {
      try { 
//          client.close(); 
//          reader.close();
          listeClient.remove(ic);
      }
      catch (Exception e ){ }
    }
  }
}
