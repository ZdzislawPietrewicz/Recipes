package recipes.errors;

public class RecipeDoesNotExist extends RuntimeException {
    public RecipeDoesNotExist(String message) {
        super(message);
    }
}
