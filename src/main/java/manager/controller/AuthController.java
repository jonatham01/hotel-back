package manager.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manager.dto.AuthenticationRequest;
import manager.dto.AuthenticationResponse;
import manager.dto.UserRequestDTO;
import manager.dto.UserResponseDTO;
import manager.entity.User;
import manager.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("save")
    public ResponseEntity<UserResponseDTO> createNewUser(@RequestBody UserRequestDTO dto) {
        try{ return ResponseEntity.ok(authService.registerUser(dto));
        }catch (Exception e){ throw  new RuntimeException("System could not create new user"); }
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try{ return ResponseEntity.ok(authService.login(authenticationRequest));
        }catch (Exception e){ throw  new RuntimeException("System could not authenticate user"); }
    }

    @GetMapping("logout")
    public ResponseEntity<String> logOut(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("logout successful");
    }

    @PreAuthorize("hasAuthority('READ_MY_PROFILE')")
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestBody String token){
        User user= authService.findLoggedInUser(token);
        return  ResponseEntity.ok(user);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String token) {
        boolean isTokenValid = authService.validate(token);
        return ResponseEntity.ok(isTokenValid);
    }



}
