package org.example.server.command_handlers;

import org.example.contract.command.UpdateCommand;
import org.example.contract.responses.Response;
import org.example.contract.responses.ResponseWithMessage;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;

public class UpdateCommandHandler extends CommandHandler<UpdateCommand> {
    @Override
    public Response handle(UpdateCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if (collectionManager.getSize() == 0) {
            return new ResponseWithMessage(StatusCode._200_SUCCESS_, "коллекция пустая");
        }else{
            long updateId = command.getId();
            if (!collectionManager.isIdExists(updateId)) {
                return new ResponseWithMessage(StatusCode._400_CLIENT_ERROR,"такого id не существует!" );
            } else {
                collectionManager.updateById(updateId, command.getProductDTO());
                return new ResponseWithMessage(StatusCode._200_SUCCESS_, "продукт с id: " + updateId + " был обновлен");
            }
        }
    }
}
