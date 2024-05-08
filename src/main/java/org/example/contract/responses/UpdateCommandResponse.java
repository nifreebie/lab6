package org.example.contract.responses;

import lombok.Getter;
import org.example.contract.utils.StatusCode;
@Getter
public class UpdateCommandResponse extends Response{
    private String message;
    public UpdateCommandResponse(StatusCode statusCode, String message) {
        super(statusCode);
        this.message = message;
    }
}
