package org.example.client.utils;

import org.example.contract.command.Command;
import org.example.contract.command.ExitCommand;
import org.example.contract.exceptions.ClosureFailedException;
import org.example.contract.responses.ExitResponse;
import org.example.contract.responses.Response;
import org.example.contract.utils.StatusCode;

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
        if(request instanceof ExitCommand){
            try{
                client.close();
                return new ExitResponse(StatusCode._200_SUCCESS_);
            } catch (ClosureFailedException e) {
                return new ExitResponse(StatusCode._400_CLIENT_ERROR);
            }
        }
        sendCommand(request);
        try {
            Response response = (Response) recieveObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public <T extends Command> void sendCommand(T request)  {
        try{ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        oos.close();

        sendData(baos.toByteArray());}
        catch(SocketException e){
            System.out.println("Сервер не запущен!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendData(byte[] bytes) throws IOException {
        client.getOutputStream().write(bytes);

    }

    private Object recieveObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(this.client.getInputStream());
        return objectInputStream.readObject();
    }
}
