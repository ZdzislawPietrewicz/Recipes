package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class Controller {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipe(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe of given id not found");
        }
        return recipe.get();
    }

    @PostMapping("/new")
    public Map<String, Long> saveRecipe(@Valid @RequestBody Recipe recipe) {
        recipeService.saveRecipeToDB(recipe);
        return Collections.singletonMap("id", recipe.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Long id){
        return recipeService.updateRecipe(recipe, id);
    }
    @GetMapping(value = "/search", params = "category")
    public List<Recipe> getRecipesByCategory (@RequestParam String category){
        return recipeService.findRecipeByCategoryIngoreCaseOrderByDateDesc(category);
    }
    @GetMapping(value = "/search", params = "name")
    public List<Recipe> getRecipesByName (@RequestParam String name){
        return  recipeService.findRecipeByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

}
