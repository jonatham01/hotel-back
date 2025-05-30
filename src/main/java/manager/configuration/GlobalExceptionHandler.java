package manager.configuration;


import jakarta.servlet.http.HttpServletRequest;
import manager.util.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e, HttpServletRequest request) {
        ApiError error = new ApiError();
        error.setBackendMessage(e.getMessage());
        error.setMessage("Internal server error, please try again");
        String originalUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
        if (originalUri == null) {
            originalUri = request.getRequestURI();
        }
        error.setPath(originalUri);
        error.setMethod(request.getMethod());
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(500).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> HandlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        ApiError error = new ApiError();
        error.setBackendMessage(e.getMessage());
        error.setMessage("petition error has been detected");
        error.setPath(request.getRequestURI());
        error.setMethod(request.getMethod());
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {

        ApiError error = new ApiError();
        error.setBackendMessage(e.getLocalizedMessage());
        error.setMessage("Access Denied. You don't have permission to access this resource");
        error.setPath(request.getRequestURI());
        error.setMethod(request.getMethod());
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);//403
    }
}
