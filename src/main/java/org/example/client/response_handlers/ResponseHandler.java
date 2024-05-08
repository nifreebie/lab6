package org.example.client.response_handlers;

import org.example.contract.responses.Response;

public interface ResponseHandler {
    String handleResponse(Response response);
}
