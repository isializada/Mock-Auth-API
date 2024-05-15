package cloud.celonis.connector.mockapi.service;

import cloud.celonis.connector.mockapi.model.AuthRequest;
import cloud.celonis.connector.mockapi.util.MockCredentials;
import cloud.celonis.connector.mockapi.util.MockReponse;
import cloud.celonis.connector.mockapi.util.MockToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class AuthService {

    public ResponseEntity<String> authenticateResourceOwner(AuthRequest authRequest, String authHeader) {
        String username = authRequest != null ? authRequest.getUsername() : null;
        String password = authRequest != null ? authRequest.getPassword() : null;
        String clientId = authRequest != null ? authRequest.getClientId() : null;
        String clientSecret = authRequest != null ? authRequest.getClientSecret() : null;

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String decodedAuth = new String(Base64.getDecoder().decode(authHeader.substring(6)));
            String[] credentials = decodedAuth.split(":");
            clientId = credentials[0];
            clientSecret = credentials[1];
        }

        if (MockCredentials.USERNAME.equals(username) && MockCredentials.PASSWORD.equals(password) && MockCredentials.CLIENT_ID.equals(clientId) && MockCredentials.CLIENT_SECRET.equals(clientSecret)) {
            return ResponseEntity.ok(MockToken.OAUTH_RESOURCE_OWNER_TOKEN);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    public ResponseEntity<String> authenticateClientCredentials(AuthRequest authRequest, String authHeader) {
        String clientId = authRequest != null ? authRequest.getClientId() : null;
        String clientSecret = authRequest != null ? authRequest.getClientSecret() : null;

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String decodedAuth = new String(Base64.getDecoder().decode(authHeader.substring(6)));
            String[] credentials = decodedAuth.split(":");
            clientId = credentials[0];
            clientSecret = credentials[1];
        }

        if (MockCredentials.CLIENT_ID.equals(clientId) && MockCredentials.CLIENT_SECRET.equals(clientSecret)) {
            return ResponseEntity.ok(MockToken.OAUTH_CLIENT_CREDENTIALS_TOKEN);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid client credentials");
    }

    public ResponseEntity<String> authenticateBasic(String username, String password) {
        if (MockCredentials.USERNAME.equals(username) && MockCredentials.PASSWORD.equals(password)) {
            return ResponseEntity.ok(MockToken.BASIC_AUTH_TOKEN);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid basic credentials");
    }

    public ResponseEntity<String> getResourceOwnerData(String token) {
        if (("Bearer " + MockToken.OAUTH_RESOURCE_OWNER_TOKEN).equals(token)) {
            return ResponseEntity.ok(MockReponse.MOCK_JSON_DATA);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    public ResponseEntity<String> getClientCredentialsData(String token) {
        if (("Bearer " + MockToken.OAUTH_CLIENT_CREDENTIALS_TOKEN).equals(token)) {
            return ResponseEntity.ok(MockReponse.MOCK_JSON_DATA);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    public ResponseEntity<String> getBasicData(String base64) {
        if (MockToken.BASIC_AUTH_TOKEN.equals(base64)) {
            return ResponseEntity.ok(MockReponse.MOCK_JSON_DATA);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid basic auth");
    }
}
