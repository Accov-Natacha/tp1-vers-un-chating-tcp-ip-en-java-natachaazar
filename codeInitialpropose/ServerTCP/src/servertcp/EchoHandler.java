package servertcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class EchoHandler extends Thread {
  Socket client;
  EchoHandler (Socket client) {
    this.client = client;
  }
  @Override
  public void run () {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
      //writer.println("type 'bye' to disconnect");

      while (true) {
        String line = reader.readLine();
        if (line.equals(".")) {
          writer.println("bye!");
          break;
        }
        writer.println("je reponds " + line);
      }
    }
    catch (Exception e) {
      System.out.println("ERROR"+e);
      System.err.println("Exception caught: client disconnected.");
    }
    finally {
      try { client.close(); }
      catch (Exception e ){ }
    }
  }
}
