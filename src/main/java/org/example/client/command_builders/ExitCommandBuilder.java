package org.example.client.command_builders;

import org.example.contract.command.Command;

public class ExitCommandBuilder implements CommandBuilder{
    @Override
    public Command build(String[] str) {
        System.out.println("Завершение работы программы...");
        System.exit(0);
        return null;
    }
}
