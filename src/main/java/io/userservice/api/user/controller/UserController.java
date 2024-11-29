package io.userservice.api.user.controller;

import java.util.List;
import io.userservice.api.user.controller.dto.req.CreateUserRequest;
import io.userservice.api.user.controller.dto.req.UpdateUserRequest;
import io.userservice.api.user.controller.dto.res.RelationTypeResponse;
import io.userservice.api.user.controller.dto.res.UserResponse;
import io.userservice.api.user.controller.dto.res.UserWaitingListResponse;
import io.userservice.api.user.domain.dto.in.RetrieveUserWaitingListCommand;
import io.userservice.api.user.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(UserResponse.from(userService.createUser(request.toCommand())));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> retrieveAllUsers() {
        return ResponseEntity.ok(UserResponse.from(userService.getAllUsers()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> retrieveUser(@PathVariable("userId") long userId) {
        return ResponseEntity.ok(UserResponse.from(userService.getUserById(userId)));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable long userId,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(UserResponse.from(userService.updateUser(request.toCommand(userId))));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable long userId) {
        return ResponseEntity.ok(UserResponse.from(userService.deleteUser(userId)));
    }

    @PatchMapping("/init")
    public ResponseEntity<Integer> initUsers() {
        return ResponseEntity.ok(userService.initUsers());
    }

    /*
     * TODO: Retrieve the waiting list from concurrently active game sessions.
     *
     * This task involves:
     * 1. Identifying all currently ongoing game parties (concurrency).
     * 2. Fetching the waiting list associated with each active session.
     */
    @GetMapping("/waiting-list")
    public ResponseEntity<UserWaitingListResponse> retrieveWaitingList(
            @RequestParam long partyId) {
        return ResponseEntity.ok(UserWaitingListResponse.from(
                userService.retrieveWaitingList(new RetrieveUserWaitingListCommand(partyId))));
    }

    @GetMapping("/relation-types")
    public ResponseEntity<RelationTypeResponse> retrieveRelationType() {
        return ResponseEntity.ok(RelationTypeResponse.from(userService.getRelationType()));
    }
}
