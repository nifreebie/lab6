package org.example.client.utils;

import org.example.contract.responses.ExecuteScriptResponse;
import org.example.contract.responses.ExitResponse;
import org.example.contract.responses.Response;
import org.example.contract.responses.ResponseWithMessage;
import org.example.contract.utils.StatusCode;

public class ResponseHandler {
    public String handleResponse(Response response){
        String output = "";
        if(response instanceof ResponseWithMessage responseWithMessage){
            output = switch (responseWithMessage.getStatusCode()){
                case _200_SUCCESS_ -> "Успешно: " + responseWithMessage.getMessage();
                case _400_CLIENT_ERROR -> "Ошибка: " + responseWithMessage.getMessage();
                case _500_SERVER_ERROR -> "Ошибка сервера: " + responseWithMessage.getMessage();
            };
            return output.trim();
        } else if (response instanceof ExecuteScriptResponse executeScriptResponse) {
            for(Response r: executeScriptResponse.getResponseList()){
                output = output.concat(this.handleResponse(r)).concat("\n");
            }
        } else if (response instanceof ExitResponse) {
            if(response.getStatusCode() == StatusCode._200_SUCCESS_){
                System.out.println("Завершение работы клиента...");
                System.exit(0);
            }else{
                output = "Ошибка: не удалось завершить работу клиента";
            }

        }
        return output.trim();
    }
}
