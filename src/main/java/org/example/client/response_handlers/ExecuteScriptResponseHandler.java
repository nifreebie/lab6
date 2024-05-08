package org.example.client.response_handlers;

import lombok.NoArgsConstructor;
import org.example.contract.responses.ExecuteScriptCommandResponse;
import org.example.contract.responses.Response;

import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
public class ExecuteScriptResponseHandler implements ResponseHandler{
    @Override
    public String handleResponse(Response response){
        ExecuteScriptCommandResponse executeScriptCommandResponse = (ExecuteScriptCommandResponse) response;
        String output = "";
        for(Response r: executeScriptCommandResponse.getResponseList()){
            String commandName = r.getClass().toString().substring(37,r.getClass().toString().length()-15);
            try {
                ResponseHandler responseHandler = (ResponseHandler) Class.forName("org.example.client.response_handlers." + commandName + "ResponseHandler").getConstructor().newInstance();
                output = output.concat(responseHandler.handleResponse(r)).concat("\n");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return output;
    }
}
