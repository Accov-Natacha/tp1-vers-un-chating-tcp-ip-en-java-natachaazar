package servertcp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionListeClients {
    List<InfoClients> listeClients;
    
    public GestionListeClients() {
        listeClients = new ArrayList<>();
    }
    
    public void add(InfoClients c){
        listeClients.add(c);
    }
    
    public void remove(InfoClients c) throws IOException{
        c.getReader().close();
        c.getWriter().close();
        c.getServiceClientSocket().close();
        listeClients.remove(c);
    }
    
    public void sendMessageToAll(String m) {
        System.out.printf("A transmettre %s\n",m);
        for (InfoClients ic : listeClients ) {
            System.out.printf("A transmettre %s à %s\n",m, ic.getServiceClientSocket().getRemoteSocketAddress());
            ic.getWriter().println(m);
        }
    }
}