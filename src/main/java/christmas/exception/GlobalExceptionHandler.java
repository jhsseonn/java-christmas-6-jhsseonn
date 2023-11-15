package christmas.exception;

public class GlobalExceptionHandler {
    public void isInputInteger(String input, String errorMessage){
        try{
            Integer.parseInt(input);
        } catch (NumberFormatException e){
            System.out.printf("%s %s", "[ERROR]", errorMessage);
        }
    }
}
