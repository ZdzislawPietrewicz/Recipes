package recipes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @NotBlank
    private String description;

    @Size(min = 1)
    private String [] ingredients;

    @Size(min = 1)
    private String [] directions;

}
