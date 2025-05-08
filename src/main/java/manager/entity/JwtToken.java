package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "jwt_token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2048)
    private String token;
    private Date expires;
    private boolean isValid;
    private Long userId;

}
