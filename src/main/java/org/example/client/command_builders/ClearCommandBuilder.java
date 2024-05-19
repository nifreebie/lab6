package org.example.client.command_builders;

import lombok.NoArgsConstructor;
import org.example.contract.command.ClearCommand;
import org.example.contract.command.Command;
import org.example.contract.command.ShowCommand;
import org.example.contract.exceptions.ExtraArgumentException;

@NoArgsConstructor
public class ClearCommandBuilder implements CommandBuilder{
    @Override
    public Command build(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
        return new ClearCommand();
    }
}
