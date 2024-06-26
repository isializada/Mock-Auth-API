package cloud.celonis.connector.mockapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
}
