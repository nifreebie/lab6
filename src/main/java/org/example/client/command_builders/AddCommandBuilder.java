package org.example.client.command_builders;

import lombok.NoArgsConstructor;
import org.example.client.utils.ProductCreator;
import org.example.contract.command.AddCommand;
import org.example.contract.command.Command;
import org.example.contract.exceptions.ExtraArgumentException;
import org.example.contract.model.ProductDTO;
@NoArgsConstructor
public class AddCommandBuilder implements CommandBuilder {
    @Override
    public Command build(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
        ProductCreator productCreator = new ProductCreator();
        ProductDTO productDTO = productCreator.createNewProduct();
        return new AddCommand(productDTO);
    }
}
