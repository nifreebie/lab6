package org.example.client.utils;

import lombok.Getter;
import org.example.contract.exceptions.ClosureFailedException;
import org.example.contract.exceptions.ConnectionFailedException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPclient {
    private final String host;
    private final int port;
    private Socket socket;
    @Getter
    private InputStream inputStream;
    @Getter
    private OutputStream outputStream;

    public TCPclient(String host, int port){
        this.host = host;
        this.port = port;
    }
    public void run() throws IOException {
        connect();
    }
    private void connect() throws IOException {
        this.socket = new Socket();
        socket.connect(new InetSocketAddress(this.host, this.port));

        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }
    public void close() throws ClosureFailedException {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            throw new ClosureFailedException();
        }
    }

}
