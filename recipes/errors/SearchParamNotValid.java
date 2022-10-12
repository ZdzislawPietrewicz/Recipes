package recipes.errors;

public class SearchParamNotValid extends RuntimeException{
    public SearchParamNotValid(String message) {
        super(message);
    }
}
