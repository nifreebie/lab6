package org.example.contract.responses;

import lombok.Getter;
import lombok.Setter;
import org.example.contract.model.Product;
import org.example.contract.utils.StatusCode;

import java.util.LinkedHashSet;
@Getter
@Setter
public class ShowCommandResponse extends Response {
    LinkedHashSet<Product> products;
    public ShowCommandResponse(StatusCode statusCode, String message,LinkedHashSet<Product> products ) {

        super(statusCode);
        this.products = products;

    }
}
