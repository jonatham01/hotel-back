package manager.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
