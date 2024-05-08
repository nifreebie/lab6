package org.example.client.validation.rules;

public class FloatRule implements Rule {
    @Override
    public boolean check(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String errorMessage() {
        return "Поле должно быть числового типа!";
    }
}
