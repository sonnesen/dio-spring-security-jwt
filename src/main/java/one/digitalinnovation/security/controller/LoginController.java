package one.digitalinnovation.security.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.security.dto.LoginRequest;
import one.digitalinnovation.security.dto.LoginResponse;
import one.digitalinnovation.security.model.User;
import one.digitalinnovation.security.repository.UserRepository;
import one.digitalinnovation.security.security.JWTCreator;
import one.digitalinnovation.security.security.JWTObject;
import one.digitalinnovation.security.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SecurityProperties securityProperties;

    @PostMapping("/login")
    public LoginResponse logar(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null) {
            boolean passwordOk = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos.");
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + securityProperties.getExpiration())));
            jwtObject.setRoles(user.getRoles());
            loginResponse.setToken(JWTCreator.create(securityProperties.getPrefix(), securityProperties.getKey(), jwtObject));
            return loginResponse;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos.");
        }
    }
}
