package org.example.server.command_handlers;

import org.example.contract.command.RemoveByIdCommand;
import org.example.contract.responses.RemoveByIdCommandResponse;
import org.example.contract.responses.Response;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;

public class RemoveByIdCommandHandler extends CommandHandler<RemoveByIdCommand> {

    @Override
    public Response handle(RemoveByIdCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if(collectionManager.getSize() == 0){
            return new RemoveByIdCommandResponse(StatusCode._200_SUCCESS_, "коллекция пуста");
        } else{
            long findId = command.getId();
            if (!collectionManager.isIdExists(findId)) return new RemoveByIdCommandResponse(StatusCode._404_NOT_FOUND, "такого id не существует");
            else{
                collectionManager.removeById(findId);
                return new RemoveByIdCommandResponse(StatusCode._200_SUCCESS_, "✓Продукт с id: " + findId + " был удален");
            }
        }
    }
}
