package org.example.client.utils;

import lombok.Getter;
import lombok.Setter;
import org.example.contract.utils.BufferedLineReader;
@Getter
@Setter
public class ClientAppContainer {
    private static ClientAppContainer instance;
    private BufferedLineReader bufferedLineReader;

    private ClientAppContainer(){}

    public static ClientAppContainer getInstance(){
        if (instance == null) {
            instance = new ClientAppContainer();
        }
        return instance;
    }
}
