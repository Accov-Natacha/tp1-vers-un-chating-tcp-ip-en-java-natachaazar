package servertcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class InfoClients {

    private Socket serviceClientSocket;

    private BufferedReader reader;

    private PrintWriter writer;
    
    public InfoClients(Socket s) throws IOException {
        serviceClientSocket=s;
        reader = getInput(s);
        writer = getoutput(s);
    }

    BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    PrintWriter getoutput(Socket p) throws IOException {
        return new PrintWriter(new OutputStreamWriter(p.getOutputStream()),true);
    }

    public Socket getServiceClientSocket() {
        return serviceClientSocket;
    }

    public void setServiceClientSocket(Socket serviceClientSocket) {
        this.serviceClientSocket = serviceClientSocket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

}