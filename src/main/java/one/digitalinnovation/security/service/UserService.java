package one.digitalinnovation.security.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.security.model.User;
import one.digitalinnovation.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(passwordEncoder.encode(pass));
        userRepository.save(user);
    }
}
