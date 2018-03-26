package Clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP {

    private static BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }
    
    private static BufferedReader getInput(InputStream is) throws IOException {
        return new BufferedReader(new InputStreamReader(is));
    }
  
    private static PrintWriter getoutput(Socket p) throws IOException{
        return new PrintWriter (new OutputStreamWriter(p.getOutputStream()),true);
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        //Se connecté au port 2000
        Socket l=null;
        try{
        l = new Socket("localhost",2000);
        System.out.println(l.getLocalSocketAddress());
        BufferedReader entreeSock = getInput(l);
         BufferedReader stdin = getInput(System.in);
        PrintWriter sortieSock = getoutput(l);
        
        ThreadInputClient threadinout=new ThreadInputClient(stdin, sortieSock);
        ThreadSocketClient threadsocket = new ThreadSocketClient(entreeSock);
        threadinout.start();
        threadsocket.start();
        threadinout.join();
        threadsocket.join();
        
        //Envoyer une chaine
//        for(int i=0;i<100;i++){
//            sortieSock.printf("#%3d:Bonjour\n",i);
//            sortieSock.flush();
//            System.out.println(entreeSock.readLine());
//           
//        }
        }
        finally{
            if(l!=null){
                l.close();
            }
        }
    }
    
}
