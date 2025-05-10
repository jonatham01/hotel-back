package manager.configuration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import manager.entity.JwtToken;
import manager.entity.User;
import manager.repository.JwtTokenRepository;
import manager.service.JwtTokenService;
import manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtService;
    private final UserService userService;
    private final JwtTokenRepository jwtRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(token)  ) {
            filterChain.doFilter(request, response);
            return;
        }
        Optional<JwtToken> jwtUserToken = jwtRepository.findByToken(token);
        boolean isValid = validateToken(jwtUserToken);
        if (!isValid) {
            filterChain.doFilter(request, response);
            return;
        }
        String username= jwtService.extractUsername(token);
        User userDetails = userService.findOneByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(Optional<JwtToken> jwtUserToken) {
        if (jwtUserToken.isEmpty()) {
            return false;
        }
        JwtToken jwtToken = jwtUserToken.get();
        Date now = new Date(System.currentTimeMillis());
        boolean valid = jwtToken.isValid() && jwtToken.getExpires().after(now);
        if (!valid) {
            updateTokenStatus(jwtToken);
        }
        return valid;

    }

    private void updateTokenStatus(JwtToken jwtToken) {
        jwtToken.setValid(false);
        jwtRepository.save(jwtToken);
    }
}