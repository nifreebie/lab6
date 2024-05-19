package org.example.client.utils;

import org.example.client.command_builders.CommandBuilder;
import org.example.contract.command.Command;

public interface CommandBuilderProvider<T extends CommandBuilder>{
    T provide(Class<? extends Command> commandClass);
}
