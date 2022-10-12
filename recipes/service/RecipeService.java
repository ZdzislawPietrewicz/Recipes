package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import recipes.errors.RecipeDoesNotExist;
import recipes.errors.SearchParamNotValid;
import recipes.model.Recipe;
import recipes.persistance.RecipeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
        return new ResponseEntity<>("Recipe with given id deleted", HttpStatus.NO_CONTENT); // 204 no content
    }

    public ResponseEntity<String> updateRecipe(Recipe recipe, Long id) {
        Optional<Recipe> recipeToUpdate = getRecipe(id);
        recipeToUpdate.ifPresentOrElse((rec) -> {
            rec.setName(recipe.getName());
            rec.setDescription(recipe.getDescription());
            rec.setCategory(recipe.getCategory());
            rec.setIngredients(recipe.getIngredients());
            rec.setDirections(recipe.getDirections());
            recipeRepository.save(rec);
        }, () -> {
            throw new RecipeDoesNotExist("Recipe with this id does not exist");
        });
        return new ResponseEntity<>("Recipe given with id is updated", HttpStatus.NO_CONTENT); // 204
    }

    public List<Recipe> findRecipeByCategoryIngoreCaseOrderByDateDesc(String category) {
        return recipeRepository.findRecipesByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findRecipeByNameContainingIgnoreCaseOrderByDateDesc(String name) {
        return recipeRepository.findRecipesByNameContainsIgnoreCaseOrderByDateDesc(name);
    }

}
