package recipes.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RecipeDoesNotExist.class)
    public ResponseEntity<CustomErrorMessage> handleRecipeDoesNotExist(RecipeDoesNotExist e, WebRequest webRequest){
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SearchParamNotValid.class)
    public ResponseEntity<CustomErrorMessage>handleSearchParamNotVAlid(SearchParamNotValid e, WebRequest webRequest) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }
}
