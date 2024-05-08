package org.example.contract.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.contract.utils.StatusCode;
@Getter
public class AddCommandResponse extends Response {
    private String message;

    public AddCommandResponse(StatusCode statusCode, String message) {
        super(statusCode);
        this.message = message;
    }
}
