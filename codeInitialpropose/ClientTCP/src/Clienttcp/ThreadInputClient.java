
package Clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadInputClient extends Thread{
    String line;
    BufferedReader stdin;
    PrintWriter sortieSock;
            
    ThreadInputClient (BufferedReader stdin,PrintWriter sortieSock) throws IOException {
      this.stdin=stdin;
      this.sortieSock=sortieSock;
    }
      @Override
  public void run () {
        try {
            System.out.println("Je rentre thread input");
            while(!(line=stdin.readLine()).equals(".")){
                sortieSock.printf("%s\n",line);
            }
            sortieSock.printf(".\n");
        } catch (IOException ex) {
            Logger.getLogger(ThreadInputClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  }
}
