package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.Recipe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Recipe recipe = recipeMap.get(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe of given id not found");
        }
        return recipeMap.get(id);
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeMap.size() + 1;
        recipeMap.put(id, recipe);
        return Collections.singletonMap("id", id);
    }
}
