package cloud.celonis.connector.mockapi.controller;
import cloud.celonis.connector.mockapi.model.AuthRequest;
import cloud.celonis.connector.mockapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/resource-owner")
    public ResponseEntity<String> authenticateResourceOwner(
            @RequestBody(required = false) AuthRequest authRequest,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        return authService.authenticateResourceOwner(authRequest, authHeader);
    }

    @PostMapping("/client-credentials")
    public ResponseEntity<String> authenticateClientCredentials(
            @RequestBody(required = false) AuthRequest authRequest,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        return authService.authenticateClientCredentials(authRequest, authHeader);
    }

    @PostMapping("/basic")
    public ResponseEntity<String> authenticateBasic(@RequestBody AuthRequest authRequest) {
        return authService.authenticateBasic(authRequest.getUsername(), authRequest.getPassword());
    }

    @GetMapping("/resource-owner/data")
    public ResponseEntity<String> getResourceOwnerData(@RequestHeader("Authorization") String token) {
        return authService.getResourceOwnerData(token);
    }

    @GetMapping("/client-credentials/data")
    public ResponseEntity<String> getClientCredentialsData(@RequestHeader("Authorization") String token) {
        return authService.getClientCredentialsData(token);
    }

    @GetMapping("/basic/data")
    public ResponseEntity<String> getBasicData(@RequestHeader("Authorization") String base64) {
        return authService.getBasicData(base64);
    }
}
