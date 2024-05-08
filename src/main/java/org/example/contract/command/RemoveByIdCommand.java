package org.example.contract.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class RemoveByIdCommand implements Command{
    private long id;
}
