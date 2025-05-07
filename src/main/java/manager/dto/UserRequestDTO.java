package manager.dto;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private String name;
    private String password;
    private String repeatedPassword;
}
