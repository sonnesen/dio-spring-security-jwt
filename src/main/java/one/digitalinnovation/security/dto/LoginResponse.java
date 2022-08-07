package one.digitalinnovation.security.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String login;
    private String token;
}
