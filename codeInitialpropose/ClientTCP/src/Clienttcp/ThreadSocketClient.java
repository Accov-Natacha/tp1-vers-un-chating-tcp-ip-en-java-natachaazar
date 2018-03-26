
package Clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSocketClient extends Thread {
  
    BufferedReader entreeSock;
    String line;
    ThreadSocketClient (BufferedReader entreeSock) throws IOException {
        this.entreeSock=entreeSock;   
    }
    public void run () {
        try {
            System.out.println("Je rentre ici thread socket");
            while(!(line=entreeSock.readLine()).equals("bye")){
                   System.out.println(line);
            }
            System.out.println(line);
        } catch (IOException ex) {
            Logger.getLogger(ThreadInputClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  }
}
