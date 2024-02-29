
package it.itsrizzoli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App 
{
    static int portNumber = 1234;
    public static void main(String[] args)
    { System.out.println("Server started!");


        ServerSocket serverSocket = null;
        serverSocket = getServerSocket();

        Socket clientSocket = null;
        clientSocket = getClientSocket(serverSocket);
        BufferedReader in;
        in = getBufferedReader(clientSocket);
        PrintWriter out = null;
        out = getPrintWriter(out, clientSocket);
        readerLoop(in, out);
    }

    private static void readerLoop(BufferedReader in, PrintWriter out) {
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

    private static PrintWriter getPrintWriter(PrintWriter out, Socket clientSocket) {
        try {
             out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    private static BufferedReader getBufferedReader(Socket clientSocket) {
        BufferedReader in;
        try {
             in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return in;
    }

    private static Socket getClientSocket(ServerSocket serverSocket) {
        Socket clientSocket;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientSocket;
    }

    private static ServerSocket getServerSocket() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serverSocket;
    }
}
