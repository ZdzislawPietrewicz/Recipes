package recipes.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String directions;

}
