package io.userservice.api.user.presentation.feignClient;

import io.userservice.api.user.business.UserService;
import io.userservice.api.user.presentation.controller.dto.res.UserRelationTypeRepsonse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 10.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserClientController {

    private final UserService userService;

    @GetMapping("/authentication")
    public ResponseEntity<UserRelationTypeRepsonse> getUserAuthenticationByName(
            @RequestParam(name = "name") String name
    ) {
        return ResponseEntity.ok(UserRelationTypeRepsonse.from(userService.getUserLoginByName(name)));
    }

    @PatchMapping("/{userId}/authentication/login")
    public ResponseEntity<Void> confirmLogin(
            @PathVariable long userId) {
        userService.confirmLogin(userId);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{userId}/authentication/logout")
    public void confirmLogout(
            @PathVariable long userId) {
        userService.confirmLogout(userId);
    }
}
