package org.example.client.utils;

import org.example.contract.command.Command;
import org.example.contract.responses.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLOutput;

public class Sender {
    private final TCPclient client;

    public Sender(TCPclient client) {
        this.client = client;
    }


    public <T extends Command> Response sendRequest(T request) throws IOException {
//        if (request instanceof ExitRequest) {
//            try {
//                client.close();
//                return new ExitResponse("exit", "exit");
//            } catch (ClosureFailedException e) {
//                return new ErrorResponse(e.toString());
//            }
//        }
       try{ sendCommand(request);
        try {
            Response response = (Response) recieveObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
       }catch(SocketException e){
           throw e;
       }
    }

    public <T extends Command> void sendCommand(T request) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        oos.close();

        sendData(baos.toByteArray());
    }

    private void sendData(byte[] bytes) {
        try {
            client.getOutputStream().write(bytes);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object recieveObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(this.client.getInputStream());
        return objectInputStream.readObject();
    }
}
