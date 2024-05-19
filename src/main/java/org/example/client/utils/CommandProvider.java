package org.example.client.utils;

import org.example.contract.command.Command;

import java.security.Provider;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class CommandProvider {
    static ServiceLoader<Command> loader = ServiceLoader.load(Command.class);
    static Iterator<Command> it = loader.iterator();
    public static Command getc(String name){
        while(it.hasNext()){
            Command c = it.next();
            if(name.equals(c.getClass().getName())){
                return c;
            }
        }
        throw new ClassCastException();
    }

}
