package manager.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manager.dto.AuthenticationRequest;
import manager.dto.AuthenticationResponse;
import manager.dto.UserRequestDTO;
import manager.dto.UserResponseDTO;
import manager.entity.User;
import manager.repository.UserRepository;
import manager.service.AuthService;
import manager.service.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    @PostMapping("save")
    public ResponseEntity<UserResponseDTO> createNewUser(@RequestBody UserRequestDTO dto) {
        try{ return ResponseEntity.ok(authService.registerUser(dto));
        }catch (Exception e){ throw  new RuntimeException("System could not create new user"); }
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try{ return ResponseEntity.ok(authService.login(authenticationRequest));
        }catch(UsernameNotFoundException exception){
            throw new UsernameNotFoundException(exception.getMessage());
        }
        catch (Exception e){ throw  new RuntimeException("System could not authenticate user"); }
    }

    @GetMapping("logout")
    public ResponseEntity<String> logOut(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("logout successful");
    }


    @PostMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestParam String jwt) {
        String username = jwtTokenService.extractUsername(jwt);
        User user = userRepository.findByUsername(username).orElseThrow(()-> {
            throw new RuntimeException("NO USER FOUND");
        });
        return ResponseEntity.ok(user);

    }
    @PreAuthorize("permitAll()")
    @GetMapping("validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String token) {
        boolean isTokenValid = authService.validate(token);
        return ResponseEntity.ok(isTokenValid);
    }



}
