package org.example.contract.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.contract.utils.StatusCode;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
public class Response implements Serializable {
    protected StatusCode statusCode;
}
