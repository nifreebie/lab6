package org.example.client.validation.rules;

public class IntRule implements Rule {
    public boolean check(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String errorMessage() {
        return "Поле должно быть целочисленного типа!";
    }
}
