package org.example.client.command_builders;

import lombok.NoArgsConstructor;
import org.example.client.utils.CommandWithIdArgument;
import org.example.client.utils.ProductCreator;
import org.example.contract.command.Command;
import org.example.contract.command.UpdateCommand;
import org.example.contract.exceptions.NoArgumentException;
import org.example.contract.model.ProductDTO;

@NoArgsConstructor
public class UpdateCommandBuilder implements CommandBuilder, CommandWithIdArgument {
    @Override
    public Command build(String[] str) {
        if(str.length == 1) throw new NoArgumentException();
        if(checkArgForId(str[1])){
            if(Long.parseLong(str[1])<=0) throw new NumberFormatException();
            ProductCreator productCreator = new ProductCreator();
            ProductDTO productDTO = productCreator.createNewProduct();
            return new UpdateCommand(Long.parseLong(str[1]), productDTO);
        }else{
            throw new NumberFormatException();


        }

    }
}
