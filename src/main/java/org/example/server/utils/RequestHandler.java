package org.example.server.utils;

import org.example.contract.command.Command;
import org.example.contract.responses.Response;
import org.example.contract.utils.Serializer;

import java.nio.ByteBuffer;

public class RequestHandler {
    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    public <T extends Response> T handleRequest(ByteBuffer buffer) {
        T response;
        Command command;
        try {
            command = Serializer.deserializeObject(buffer);
            System.out.println("Получено: " + command.getClass());
            response = (T) commandManager.executeCommand(command);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
