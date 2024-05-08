package org.example.client.validation.rules;

public class StringLengthRule implements Rule{
    private final int length;

    public StringLengthRule(int length) {
        this.length = length;
    }

    @Override
    public boolean check(String value) {
        return value.length() <= length;
    }

    @Override
    public String errorMessage() {
        return "Длина строки не должна быть больше " + length;
    }
}
