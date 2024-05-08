package org.example.server.command_handlers;

import org.example.contract.command.UpdateCommand;
import org.example.contract.responses.Response;
import org.example.contract.responses.UpdateCommandResponse;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;

public class UpdateCommandHandler extends CommandHandler<UpdateCommand> {
    @Override
    public Response handle(UpdateCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if (collectionManager.getSize() == 0) {
            return new UpdateCommandResponse(StatusCode._200_SUCCESS_, "Коллекция пустая");
        }else{
            long updateId = command.getId();
            if (!collectionManager.isIdExists(updateId)) {
                return new UpdateCommandResponse(StatusCode._404_NOT_FOUND,"Такого id не существует!" );
            } else {
                collectionManager.updateById(updateId, command.getProductDTO());
                return new UpdateCommandResponse(StatusCode._200_SUCCESS_, "✓Продукт с id: " + updateId + " был обновлен");
            }
        }
    }
}
