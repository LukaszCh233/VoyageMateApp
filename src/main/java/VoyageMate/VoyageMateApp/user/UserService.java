package VoyageMate.VoyageMateApp.user;

import VoyageMate.VoyageMateApp.Role;
import VoyageMate.VoyageMateApp.config.JwtService;
import VoyageMate.VoyageMateApp.exception.ExistsException;
import VoyageMate.VoyageMateApp.exception.IncorrectPasswordException;
import VoyageMate.VoyageMateApp.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityMapper entityMapper;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EntityMapper entityMapper,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.entityMapper = entityMapper;
        this.jwtService = jwtService;
    }

    public UserDTO createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ExistsException("User with this email is registered");
        }

        user.setRole(Role.USER);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return entityMapper.mapUserToUserDTO(userRepository.save(user));
    }

    public String userAuthorization(LoginRequest loginRequest) {
        User registeredUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()
                -> new EntityNotFoundException("User not exists"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), registeredUser.getPassword())) {
            throw new IncorrectPasswordException("Incorrect email or password");
        }
        return jwtService.generateToken(registeredUser);
    }
}
