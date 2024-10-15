package VoyageMate.VoyageMateApp.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access")
public class AccessController {
    private final UserService userService;

    public AccessController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user-register")
    ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        UserDTO createUserDTO = userService.createUser(user);

        return ResponseEntity.ok(createUserDTO);
    }

    @PostMapping("/user-login")
    ResponseEntity<?> loginUser(@RequestBody LoginRequest user) {
        String jwtToken = userService.userAuthorization(user);

        return ResponseEntity.ok(jwtToken);
    }
}

