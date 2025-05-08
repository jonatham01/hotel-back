package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.UserRequestDTO;
import manager.entity.User;
import manager.repository.UserRepository;
import manager.util.Role;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(UserRequestDTO user) throws BadRequestException {
        if(StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getRepeatedPassword())){
            throw new BadRequestException("Password and Repeated Password are required");
        }
        if(!passwordEncoder.matches(user.getPassword(), user.getRepeatedPassword())){
            throw new BadRequestException("Password does not match");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(Role.ADMINISTRATOR);
        return userRepository.save(newUser);
    }
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public Optional<User> findOneByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
