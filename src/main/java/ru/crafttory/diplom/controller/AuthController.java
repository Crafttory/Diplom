package ru.crafttory.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.crafttory.diplom.component.JwtRequest;
import ru.crafttory.diplom.component.Login;
import ru.crafttory.diplom.exception.UserAuthException;
import ru.crafttory.diplom.service.AuthService;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<Login> login(@RequestBody JwtRequest authRequest) {
        final Login token;
        try {
            token = authService.login(authRequest);
        } catch (AuthException e) {
            throw new UserAuthException(e);
        }
        return ResponseEntity.ok(token);
    }

}
