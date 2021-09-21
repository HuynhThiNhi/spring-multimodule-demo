package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.Conflict;
import com.example.demo.exception.NotFound;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers()
    {
       return ResponseEntity.ok().body(userService.getUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            throw new BadRequest();

        if(userService.findUserByUsername(user.getUsername()) != null)
            throw new Conflict();

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }
    @PostMapping("/user/update/{id}")
    private ResponseEntity<User> updateUser(@Valid @RequestBody User newUser, @PathVariable Long id,
                                            BindingResult bindingResult)
    {
        Optional<User> updatedUser = userService.findUserById(id);
        if(bindingResult.hasErrors())
            throw new BadRequest();
        if(updatedUser.isEmpty())
            throw new NotFound();

        return new ResponseEntity<>(userService.updateUser(newUser), HttpStatus.OK);

    }
    @DeleteMapping("/user/delete/{id}")
    private ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("message:", "success deleted");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
