package org.example.client.utils;

public interface CommandWithIdArgument {
    default boolean checkArgForId(String arg){
        try{
            Long.parseLong(arg);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
