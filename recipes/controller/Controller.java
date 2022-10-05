package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.Recipe;
import recipes.persistance.RecipeRepository;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipe(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe of given id not found");
        }
        return recipe.get();
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long> saveRecipe(@Valid @RequestBody Recipe recipe) {
        recipeService.saveRecipeToDB(recipe);
        return Collections.singletonMap("id", recipe.getId());
    }
    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }
}
