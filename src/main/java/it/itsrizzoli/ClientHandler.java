package it.itsrizzoli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;


public class ClientHandler {
    Socket clientSocket = null;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    void handle(){
        BufferedReader in;
        in = getBufferedReader();
        PrintWriter out = null;
        out = getPrintWriter(out);
        readerLoop(in, out);

    }
    private BufferedReader getBufferedReader() {
        BufferedReader in;
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return in;
    }
    private PrintWriter getPrintWriter(PrintWriter out) {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
    private void readerLoop(BufferedReader in, PrintWriter out) {
        String s = "";
        try {
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                out.println(s.toUpperCase(Locale.ROOT));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
