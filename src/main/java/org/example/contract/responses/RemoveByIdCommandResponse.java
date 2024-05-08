package org.example.contract.responses;

import lombok.Getter;
import org.example.contract.utils.StatusCode;
@Getter
public class RemoveByIdCommandResponse extends Response{
    private String message;
    public RemoveByIdCommandResponse(StatusCode statusCode, String message) {

        super(statusCode);
        this.message = message;
    }
}
