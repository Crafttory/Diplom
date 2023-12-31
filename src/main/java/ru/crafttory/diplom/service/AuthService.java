package ru.crafttory.diplom.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.crafttory.diplom.component.JwtRequest;
import ru.crafttory.diplom.component.Login;
import ru.crafttory.diplom.entity.User;
import ru.crafttory.diplom.security.authentication.JwtAuthentication;

import javax.security.auth.message.AuthException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public Login login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            return new Login(accessToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
