package byfayzullayev.mymed.controller.user;

import byfayzullayev.mymed.model.receive.user.SignInReceiveModel;
import byfayzullayev.mymed.model.receive.user.SignUpReceiveModel;
import byfayzullayev.mymed.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/myMed/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<?> addUser(@Valid @RequestBody SignUpReceiveModel signUpReceiveModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(signUpReceiveModel));
    }


    @GetMapping("/list")
    public HttpEntity<?> getUsersList() {
        return ResponseEntity.ok(userService.getUsersList());
    }

    @CrossOrigin
    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody SignInReceiveModel signInReceiveModel) {
        return ResponseEntity.ok(userService.login(signInReceiveModel));

    }
}