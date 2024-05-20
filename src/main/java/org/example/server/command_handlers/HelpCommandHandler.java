package org.example.server.command_handlers;

import org.example.contract.command.HelpCommand;
import org.example.contract.responses.Response;

public class HelpCommandHandler extends CommandHandler<HelpCommand>{
    @Override
    public Response handle(HelpCommand command) {
        return null;
    }
}
