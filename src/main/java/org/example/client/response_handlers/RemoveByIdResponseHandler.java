package org.example.client.response_handlers;

import lombok.NoArgsConstructor;
import org.example.contract.responses.RemoveByIdCommandResponse;
import org.example.contract.responses.Response;
import org.example.contract.utils.StatusCode;

@NoArgsConstructor
public class RemoveByIdResponseHandler implements ResponseHandler{
    @Override
    public String handleResponse(Response response) {
        RemoveByIdCommandResponse removeByIdResponse = (RemoveByIdCommandResponse) response;
        String output = "";
        if(removeByIdResponse.getStatusCode() == StatusCode._200_SUCCESS_){
            output  += "Успешно: ";
        } else if(removeByIdResponse.getStatusCode() == StatusCode._404_NOT_FOUND){
            output  += "Ошибка: ";
        }
        return output + removeByIdResponse.getMessage();
    }
}
