package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import recipes.errors.RecipeDoesNotExist;
import recipes.model.Recipe;
import recipes.persistance.RecipeRepository;

import java.util.Optional;

@Configuration
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public Recipe saveRecipeToDB(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> getRecipe(Long id) {
        Optional<Recipe> foundRecipe = recipeRepository.findById(id);
        return foundRecipe;
    }

    public ResponseEntity<String> deleteRecipe(Long id) {
        Optional<Recipe> recipeToDelete = getRecipe(id);
        recipeToDelete.ifPresentOrElse((recipe) -> {
                    recipeRepository.delete(recipe);
                },
                () -> {
                    throw new RecipeDoesNotExist("Recipe with this id does not exist");
                });
        return new ResponseEntity<>("Recipe with given id deleted",HttpStatus.NO_CONTENT);
    }
}
