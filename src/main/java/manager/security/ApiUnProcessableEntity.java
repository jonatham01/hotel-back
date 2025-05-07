package manager.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // HTTP 422
public class ApiUnProcessableEntity extends RuntimeException {
    public ApiUnProcessableEntity(String message) {
        super(message);
    }
}
