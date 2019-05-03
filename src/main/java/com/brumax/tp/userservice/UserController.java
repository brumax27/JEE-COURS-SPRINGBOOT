package com.brumax.tp.userservice;

import com.sun.jndi.toolkit.url.Uri;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collector;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getall(){
        return userService.getUsers();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody CreatUserDto dto, UriComponentsBuilder uriBuilder) {
        if ("john".equalsIgnoreCase(dto.getFirstname())) {
            throw new NoJohnAllowedExecption();
        }
        userService.addUser(dto.getLastname(), dto.getFirstname());
        URI uri = uriBuilder.path("/users/{firstname}/").buildAndExpand(dto.getFirstname()).toUri();
        return created(uri).build();
    }

    @GetMapping("/{firstname}/details/")
    public User getUserDetail(@PathVariable("firstname") String firstname){
        return userService.getUsers().stream().filter(u -> u.getFirstname().equals(firstname)).findFirst().get();
    }

    ///users/details/?room=b22&age=22
    @GetMapping("/details/")
    public User search(@RequestParam("room") String room, @RequestParam("age") Integer age){
        return null;
    }

}

@NoArgsConstructor
@AllArgsConstructor
class ErrorDTO {
    private @Getter String message;
    private @Getter int errorCode;
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NoJohnAllowedExecption extends RuntimeException {

    private final @Getter int errorCode = 1001;

    public NoJohnAllowedExecption() {
        super("no john is allowed");
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CreatUserDto {

    private String firstname;
    private String lastname;

}