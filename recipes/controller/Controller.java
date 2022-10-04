package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.Recipe;
import recipes.persistance.RecipeRepository;
import recipes.service.RecipeService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe of given id not found");
        }
        return recipe;
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long> saveRecipe(@RequestBody Recipe recipe) {
        recipeService.saveRecipeToDB(recipe);
        return Collections.singletonMap("id", recipe.getId());
    }
}
