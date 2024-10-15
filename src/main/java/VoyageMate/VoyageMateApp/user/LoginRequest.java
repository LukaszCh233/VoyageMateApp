package VoyageMate.VoyageMateApp.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginRequest {
    private String email;
    private String password;
}