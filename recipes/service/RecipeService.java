package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    public Recipe getRecipe(Long id) {
        Optional<Recipe>foundRecipe= recipeRepository.findById(id);
        if(foundRecipe.isPresent()) return foundRecipe.get();
        else return null;
    }
}
