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
    }};

    public static Command getDTO(String[] str) throws ClassNotFoundException {
        return ClientAppContainer.getInstance().getCommandBuilderProvider().provide(parser.get(str[0])).build(str);
    }
}
