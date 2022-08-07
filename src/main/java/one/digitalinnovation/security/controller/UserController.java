package one.digitalinnovation.security.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.security.dto.UserRequest;
import one.digitalinnovation.security.model.User;
import one.digitalinnovation.security.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final ModelMapper modelMapper;

    @PostMapping
    public void postUser(@RequestBody UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        service.createUser(user);
    }
}