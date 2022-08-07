package one.digitalinnovation.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String name;
    private String username;
    private String password;
    private List<String> roles;
}
