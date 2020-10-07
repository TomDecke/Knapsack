package de.knapsack.model;

/**
 * Exception to pass information to the user in case of an invalid input.
 * @author TDecke
 */
public class InputValidationException extends Exception{
    public InputValidationException(String aMessage) {
        super(aMessage);
    }
}
