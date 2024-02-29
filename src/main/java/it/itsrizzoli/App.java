
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
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader in;
        try {
             in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = null;
        try {
             out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
