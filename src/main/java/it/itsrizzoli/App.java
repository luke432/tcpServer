
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
        clientSocket = accept(serverSocket);
        ClientHandler ch = new ClientHandler(clientSocket);
        ch.handle();

    }





    private static Socket accept(ServerSocket serverSocket) {
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
