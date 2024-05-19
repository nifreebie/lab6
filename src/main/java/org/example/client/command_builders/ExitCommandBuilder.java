package org.example.client.command_builders;

import lombok.NoArgsConstructor;
import org.example.contract.command.Command;
import org.example.contract.command.ExitCommand;
import org.example.contract.exceptions.ExtraArgumentException;
@NoArgsConstructor
public class ExitCommandBuilder implements CommandBuilder{
    @Override
    public Command build(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
        return new ExitCommand();
    }
}
