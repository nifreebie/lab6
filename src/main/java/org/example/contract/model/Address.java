package org.example.contract.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class Address implements Serializable {
    @JacksonXmlProperty(localName = "street")
    private String street;


    public Address(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }
}
