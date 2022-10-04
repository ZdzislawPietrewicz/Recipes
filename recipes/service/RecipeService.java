package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import recipes.model.Recipe;
import recipes.persistance.RecipeRepository;

@Configuration
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public Recipe saveRecipeToDB(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
