package servertcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pascal Fares
 */
public class ServerTCP {
/**
     * Récupère le flux d'entrée d'une Socket et l'encapsule dans un BufferedReader 
     * Un BufferedReader permet de Lire le texte à partir d'un flux d'entrée de caractères, en mettant
     * en mémoire tampon les caractères afin de permettre une lecture efficace 
     * des caractères, des tableaux et des lignes.
     * @param p la SOcket
     * @return le BufferedReader crée
     * @throws IOException 
     */
    private static BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }
/**
     * Récupère le flus de sortie de la socket et l'encapsule dans un PrintWriter
     * Imprime des représentations formatées d'objets dans un 
     * flux de sortie de texte. Cette classe implémente toutes les méthodes 
     * d'impression trouvées dans PrintStream.
     * @param p la Socket
     * @return le PrintWriter crée
     * @throws IOException 
     */
    private static PrintWriter getoutput(Socket p) throws IOException {
        return new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws IOException {
//        //Créer une écoute sur le port 2000
//        ServerSocket l = new ServerSocket(2000);
//        System.out.println(l.getLocalSocketAddress());
//        while (true) {
//            //try-with-resource
//            try (Socket serviceSocket = l.accept()) {
//                //Acceper un client l.accept()
//                System.out.println(serviceSocket.getRemoteSocketAddress());
//                BufferedReader ir = getInput(serviceSocket);
//                PrintWriter reply = getoutput(serviceSocket);
//                String line;                
//                while ((line = ir.readLine()) != null) {                  
//                    System.out.printf("je répond ping %s\n", line);
//                    reply.printf("je répond ping %s\n", line);
//                    reply.flush();
//                }
//                //clore la socket (réalisé par try-with resource
//            }
//        }
//    }
    
    public static void main (String[] args) {
    try {
      ServerSocket server = new ServerSocket(2000);
      GestionListeClients listeClient = new GestionListeClients();
      System.out.println("ADDRESSSSS" + server.getLocalSocketAddress());
      while (true) {
        Socket client = server.accept();
        System.out.println("CLIENT "+client.getRemoteSocketAddress());
        EchoHandler handler = new EchoHandler(client,listeClient);
        handler.start();
      }
    }
    catch (Exception e) {
      System.err.println("Exception caught:" + e);
    }
  }

}
