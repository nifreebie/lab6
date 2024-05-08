package org.example.client.response_handlers;

import lombok.NoArgsConstructor;
import org.example.contract.responses.Response;
import org.example.contract.responses.UpdateCommandResponse;
import org.example.contract.utils.StatusCode;

@NoArgsConstructor
public class UpdateResponseHandler implements ResponseHandler {
    @Override
    public String handleResponse(Response response) {
        UpdateCommandResponse updateCommandResponse = (UpdateCommandResponse) response;
        String output = "";
        if(updateCommandResponse.getStatusCode() == StatusCode._200_SUCCESS_){
            output  += "Успешно: ";
        } else if(updateCommandResponse.getStatusCode() == StatusCode._404_NOT_FOUND){
            output  += "Ошибка: ";
        }
        return output + updateCommandResponse.getMessage();
    }
}
