package org.example.contract.responses;

import lombok.Getter;
import org.example.contract.utils.StatusCode;

import java.util.List;
@Getter
public class ExecuteScriptCommandResponse extends Response{
    private List<Response> responseList;
    public ExecuteScriptCommandResponse(StatusCode statusCode,List<Response> responseList ) {
        super(statusCode);
        this.responseList = responseList;
    }
}
