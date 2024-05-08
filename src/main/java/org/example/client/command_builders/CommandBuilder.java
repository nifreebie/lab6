package org.example.client.command_builders;

import org.example.contract.command.Command;

public interface CommandBuilder {
    Command build(String[] str);
}
