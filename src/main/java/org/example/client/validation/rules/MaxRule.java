package org.example.client.validation.rules;

public class MaxRule implements Rule{
    private final Float maxValue;

    public MaxRule(Float maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean check(String value) {
        try{
            return Float.parseFloat(value) <= maxValue;
        }catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public String errorMessage() {
        return "Поле не должно быть больше " + maxValue;
    }
}
