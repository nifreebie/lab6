package org.example.client.response_handlers;

import lombok.NoArgsConstructor;
import org.example.contract.model.Product;
import org.example.contract.responses.Response;
import org.example.contract.responses.ShowCommandResponse;

@NoArgsConstructor
public class ShowResponseHandler implements ResponseHandler{
    @Override
    public String handleResponse(Response response) {
        ShowCommandResponse showCommandResponse = (ShowCommandResponse) response;
        String output = "";
        if(showCommandResponse.getProducts() == null){
            output += "Успешно: коллекция пуста";
        }else {
            output += "Успешно:\n";
            for(Product p: showCommandResponse.getProducts()){
                output = output.concat(p.toString()).concat("\n");
            }
        }
        return output;
    }
}
