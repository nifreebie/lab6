package org.example.client.utils;

import org.example.contract.command.*;

import java.util.HashMap;

public class CommandParser {
    static HashMap<String, Class<? extends Command>> parser = new HashMap<String, Class<? extends Command>>() {{
        put("add", AddCommand.class);
        put("remove_by_id", RemoveByIdCommand.class);
        put("exit", ExitCommand.class);
        put("update", UpdateCommand.class);
        put("show", ShowCommand.class);
        put("execute_script", ExecuteScriptCommand.class);
        put("add_if_min", AddIfMinCommand.class);
        put("clear", ClearCommand.class);
        put("help", HelpCommand.class);
        put("info", InfoCommand.class);
        put("print_descending", PrintDescendingCommand.class);
        put("print_field_descending_part_number", PrintFieldDescendingPartNumberCommand.class);
        put("remove_greater", RemoveGreaterCommand.class);
        put("remove_lower", RemoveLowerCommand.class);
    }};

    public static Command getDTO(String[] str){
        return ClientAppContainer.getInstance().getCommandBuilderProvider().provide(parser.get(str[0])).build(str);
    }
}
