package recipes.persistance;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipesByCategoryIgnoreCaseOrderByDateDesc(String category);

    List<Recipe> findRecipesByNameContainsIgnoreCaseOrderByDateDesc(String name);

}
