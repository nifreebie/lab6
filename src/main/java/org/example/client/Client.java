package org.example.client;

import org.example.client.utils.ClientAppContainer;
import org.example.client.utils.ConsoleManager;
import org.example.client.utils.Sender;
import org.example.client.utils.TCPclient;
import org.example.contract.utils.BufferedLineReader;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    public static void main(String[] args){
        initClientAppContainer();
        TCPclient tcpClient = new TCPclient(SERVER_ADDRESS,SERVER_PORT);
        Sender sender = new Sender(tcpClient);
        ConsoleManager consoleManager = new ConsoleManager(sender);
        try {
            tcpClient.run();
            consoleManager.run();
        } catch (ConnectException e) {
            System.out.println("Сервер не запущен!");
        } catch (SocketTimeoutException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void initClientAppContainer(){
        ClientAppContainer app = ClientAppContainer.getInstance();
        BufferedLineReader reader =new BufferedLineReader(System.in);
        app.setBufferedLineReader(reader);

    }
}
