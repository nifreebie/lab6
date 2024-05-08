package org.example.server.command_handlers;

import org.example.contract.command.ShowCommand;
import org.example.contract.model.Product;
import org.example.contract.responses.ShowCommandResponse;
import org.example.contract.responses.Response;
import org.example.contract.utils.StatusCode;
import org.example.server.collection.CollectionManager;

import java.util.LinkedHashSet;

public class ShowCommandHandler extends CommandHandler<ShowCommand> {
    @Override
    public Response handle(ShowCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
//        if (collectionManager.getSize() == 0) {
//            this.app.getResponseWriter().write("Коллекция пустая");
//        } else {
//            for (Product p : collectionManager.getProducts()) {
//                this.app.getResponseWriter().write(p.toString());
//            }
//        }
        if(collectionManager.getSize() == 0){
            return new ShowCommandResponse(StatusCode._200_SUCCESS_, "Коллекция пуста", null);
        } else{
            return new ShowCommandResponse(StatusCode._200_SUCCESS_, "", (LinkedHashSet<Product>) collectionManager.getProducts());
        }
    }
}
