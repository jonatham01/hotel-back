package manager.configuration.customs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.util.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ApiError error = new ApiError();
        error.setBackendMessage(accessDeniedException.getLocalizedMessage());
        error.setMessage("Access Denied. You don't have permission to access this resource");
        error.setPath(request.getRequestURI());
        error.setMethod(request.getMethod());
        error.setTimestamp(LocalDateTime.now());

        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());//403

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String jsonError=mapper.writeValueAsString(error);
        response.getWriter().print(jsonError);

    }
}

