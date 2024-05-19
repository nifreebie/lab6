package org.example.server.command_handlers;

import org.example.contract.command.RemoveByIdCommand;
import org.example.contract.responses.Response;
import org.example.contract.responses.ResponseWithMessage;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;

public class RemoveByIdCommandHandler extends CommandHandler<RemoveByIdCommand> {

    @Override
    public Response handle(RemoveByIdCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if(collectionManager.getSize() == 0){
            return new ResponseWithMessage(StatusCode._200_SUCCESS_, "коллекция пуста");
        } else{
            long findId = command.getId();
            if (!collectionManager.isIdExists(findId)) return new ResponseWithMessage(StatusCode._400_CLIENT_ERROR, "такого id не существует");
            else{
                collectionManager.removeById(findId);
                return new ResponseWithMessage(StatusCode._200_SUCCESS_, "продукт с id: " + findId + " был удален");
            }
        }
    }
}
