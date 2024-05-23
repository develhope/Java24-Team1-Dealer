package com.develhope.spring.controllers;

import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Response> postUser(@RequestBody UserDTO user) {
        try {
            UserDTO newUser = userService.addUser(user);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            newUser.getRole() + " " + newUser.getUsername() + " added correctly",
                            newUser)
            );
        } catch (UserException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(new Response(200, "user found", user));
        } else {
            return ResponseEntity.status(404).body(new Response(404, "user not found"));
        }
    }
}
