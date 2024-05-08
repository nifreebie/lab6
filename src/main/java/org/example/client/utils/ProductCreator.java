package org.example.client.utils;


import org.example.contract.model.*;

public class ProductCreator {
    public ProductDTO createNewProduct() {
        ProductDTO productDTO = new ProductDTO();
        fillName(productDTO);
        fillCoordinates(productDTO);
        fillPrice(productDTO);
        fillPartNumber(productDTO);
        fillUnitOfMeasure(productDTO);
        fillOrganization(productDTO);
        return productDTO;
    }

    private void fillName(ProductDTO productDTO) {
        String name = ValidReader.readName();
        productDTO.setName(name);
    }

    private void fillCoordinates(ProductDTO productDTO) {
        Coordinates coordinates = ValidReader.readCoordinates();
        productDTO.setCoordinates(coordinates);
    }

    private void fillPrice(ProductDTO productDTO) {
        int price = ValidReader.readPrice();
        productDTO.setPrice(price);
    }

    private void fillPartNumber(ProductDTO productDTO) {
        String partNumber = ValidReader.readPartNumber();
        productDTO.setPartNumber(partNumber);
    }

    private void fillUnitOfMeasure(ProductDTO productDTO) {
        UnitOfMeasure unitOfMeasure = ValidReader.readUnitOfMeasure();
        productDTO.setUnitOfMeasure(unitOfMeasure);
    }

    private void fillOrganization(ProductDTO productDTO) {
        Organization manufacturer = ValidReader.readOrganization();
        productDTO.setManufacturer(manufacturer);
    }
}