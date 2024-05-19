package org.example.client.utils;

import lombok.Getter;
import lombok.Setter;
import org.example.client.command_builders.CommandBuilder;
import org.example.contract.utils.BufferedLineReader;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@Setter
public class ClientAppContainer {
    private static ClientAppContainer instance;
    private BufferedLineReader bufferedLineReader;
    private final Deque<File> scriptsStack = new ArrayDeque<>();
    private CommandBuilderProvider<CommandBuilder> commandBuilderProvider = new CommandBuilderProviderImpl();

    private ClientAppContainer(){}

    public static ClientAppContainer getInstance(){
        if (instance == null) {
            instance = new ClientAppContainer();
        }
        return instance;
    }
    public boolean isInteractiveMode() {
        return !scriptsStack.isEmpty();
    }
}
