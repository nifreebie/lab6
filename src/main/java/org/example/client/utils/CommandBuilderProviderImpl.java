package org.example.client.utils;

import org.example.client.command_builders.*;
import org.example.contract.command.*;

import java.util.HashMap;

public class CommandBuilderProviderImpl implements CommandBuilderProvider<CommandBuilder>{
    static HashMap<Class, CommandBuilder> commandBuilders = new HashMap<>();

    public CommandBuilderProviderImpl() {
        commandBuilders.put(AddCommand.class,new AddCommandBuilder());
        commandBuilders.put(ExecuteScriptCommand.class,new ExecuteScriptCommandBuilder());
        commandBuilders.put(ExitCommand.class,new ExitCommandBuilder());
        commandBuilders.put(RemoveByIdCommand.class,new RemoveByIdCommandBuilder());
        commandBuilders.put(ShowCommand.class,new ShowCommandBuilder());
        commandBuilders.put(UpdateCommand.class,new UpdateCommandBuilder());
        commandBuilders.put(HelpCommand.class, new HelpCommandBuilder());
        commandBuilders.put(ClearCommand.class, new ClearCommandBuilder());
        commandBuilders.put(AddIfMinCommand.class, new AddIfMinCommandBuilder());
        commandBuilders.put(InfoCommand.class, new InfoCommandBuilder());
        commandBuilders.put(PrintDescendingCommand.class, new PrintDescendingCommandBuilder());
        commandBuilders.put(PrintFieldDescendingPartNumberCommand.class, new PrintFieldDescendingPartNumberCommandBuilder());
        commandBuilders.put(RemoveLowerCommand.class, new RemoveLowerCommandBuilder());
        commandBuilders.put(RemoveGreaterCommand.class, new RemoveGreaterCommandBuilder());

    }

    @Override
    public CommandBuilder provide(Class<? extends Command> aClass) {
        return commandBuilders.get(aClass);
    }
}
