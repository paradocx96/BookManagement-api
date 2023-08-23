package com.paradocx.controller;

import com.paradocx.dto.MessageDto;
import com.paradocx.dto.user.UserSignInDto;
import com.paradocx.dto.user.UserSignUpDto;
import com.paradocx.model.User;
import com.paradocx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> signIn(@RequestBody UserSignInDto userSignInDto) {
        return userService.signIn(userSignInDto);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageDto> signUp(@RequestBody UserSignUpDto userSignUpDto) {
        return userService.signUp(userSignUpDto);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
