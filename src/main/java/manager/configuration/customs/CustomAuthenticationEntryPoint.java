package manager.configuration.customs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.util.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ApiError error = new ApiError();
        error.setBackendMessage(authException.getLocalizedMessage());
        error.setMessage("Credentials has not been provided. Please provide a valid credentials.");
        error.setPath(request.getRequestURI());
        error.setMethod(request.getMethod());
        error.setTimestamp(LocalDateTime.now());
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());//401

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String jsonError=mapper.writeValueAsString(error);
        response.getWriter().print(jsonError);
    }
}

