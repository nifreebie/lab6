package org.example.server.command_handlers;

import org.example.contract.command.AddCommand;
import org.example.contract.responses.Response;
import org.example.contract.responses.ResponseWithMessage;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;
import org.example.server.utils.ProductComparator;

public class AddCommandHandler extends CommandHandler<AddCommand> {
    public Response handle(AddCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.add(command.getProductDTO());
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
        collectionManager.save();
        return new ResponseWithMessage(StatusCode._200_SUCCESS_, "продукт добавлен");
    }
}
