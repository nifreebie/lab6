package org.example.server;

import org.example.contract.model.Product;
import org.example.server.collection.CollectionManager;
import org.example.server.collection.Storage;
import org.example.server.utils.*;

import java.io.IOException;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws IOException {
        initServerAppContainer();
        initCollection();
        RequestHandler requestHandler = new RequestHandler(ServerAppContainer.getInstance().getCommandManager(), new Logger("logs.log"));
        TCPserver server = new TCPserver(ServerAppContainer.getInstance().getCommandManager(), requestHandler, new Logger("logs.log"));
        try {
            server.openConnection();
            server.run();
        } finally {
            server.close();
        }
    }
    private static void initServerAppContainer(){
        ServerAppContainer app = ServerAppContainer.getInstance();
        CommandManager commandManager = new CommandManager();
        app.setCommandManager(commandManager);

    }
    private static void initCollection(){
        Set<Product> productCollection;
        long newLastId = 0;
        try {
            productCollection = Storage.read("collection.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CollectionManager collectionManager = new CollectionManager(productCollection);
        ServerAppContainer.getInstance().setCollectionManager(collectionManager);
        newLastId = collectionManager.getMaxId();
        ServerAppContainer.getInstance().getCollectionManager().setLastId(newLastId);
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);

    }
}
