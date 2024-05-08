package org.example.client.response_handlers;

import lombok.NoArgsConstructor;
import org.example.contract.responses.AddCommandResponse;
import org.example.contract.responses.Response;
import org.example.contract.utils.StatusCode;
@NoArgsConstructor
public class AddResponseHandler implements ResponseHandler{
    @Override
    public String handleResponse(Response response) {
        AddCommandResponse addCommandResponse = (AddCommandResponse) response;
        String output = "";
        if(addCommandResponse.getStatusCode() == StatusCode._200_SUCCESS_){
            output += "Успешно:";

        }
        return (output + addCommandResponse.getMessage()).trim();

    }
}
