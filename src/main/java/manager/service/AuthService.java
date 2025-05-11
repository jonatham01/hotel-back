package manager.service;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manager.dto.AuthenticationRequest;
import manager.dto.AuthenticationResponse;
import manager.dto.UserRequestDTO;
import manager.dto.UserResponseDTO;
import manager.entity.JwtToken;
import manager.entity.User;
import manager.repository.JwtTokenRepository;
import manager.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final JwtTokenRepository jwtTokenRepository;

    public Map<String,Object> generateClaims(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("name",user.getName());
        claims.put("role",user.getRole().name());
        claims.put("authorities",user.getAuthorities());
        return claims;
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) throws BadRequestException {
        User user = userService.save(userRequestDTO);
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().name())
                .jwt(jwtTokenService.createToken(user,generateClaims(user)))
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        if(userService.findOneByUserName(authenticationRequest.getUsername()).isEmpty()) {
            throw new UsernameNotFoundException("Username " + authenticationRequest.getUsername() + " does not exist");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authenticationManager.authenticate(authentication);
        String userName= authentication.getPrincipal().toString();
        User user = userService.findOneByUserName(userName).get();
        String token = jwtTokenService.createToken(user,generateClaims(user));
        JwtToken jwtToken = JwtToken.builder()
                .token(token)
                .isValid(true)
                .expires(jwtTokenService.getExpirationDate(token))
                .userId(user.getId())
                .build();
        jwtTokenRepository.save(jwtToken);
        AuthenticationResponse response=  new AuthenticationResponse();
        response.setToken(token);
        return response;
    }

    public void logout(HttpServletRequest request) {
        String token= jwtTokenService.extractJwtFromRequest(request);
        if(StringUtils.hasText(token))return;
        Optional<JwtToken> jwtToken = jwtTokenRepository.findByToken(token);
        if(jwtToken.get().isValid() && jwtToken.isPresent() ){
            jwtToken.get().setValid(false);
            jwtTokenRepository.save(jwtToken.get());
        }
    }
    public boolean validate(String token) {
        try {
            jwtTokenService.extractUsername(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }

    public User findLoggedInUser(String token) {
        String username = jwtTokenService.extractUsername(token);
        return userService.findOneByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
