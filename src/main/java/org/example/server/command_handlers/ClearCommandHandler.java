package org.example.server.command_handlers;

import org.example.contract.command.ClearCommand;
import org.example.contract.responses.Response;
import org.example.contract.responses.ResponseWithMessage;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;
import org.example.server.utils.ServerAppContainer;

public class ClearCommandHandler extends CommandHandler<ClearCommand>{
    @Override
    public Response handle(ClearCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.clear();
        return new ResponseWithMessage(StatusCode._200_SUCCESS_, "коллекция очищена");

    }
}
