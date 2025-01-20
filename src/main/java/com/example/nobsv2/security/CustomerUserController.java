package com.example.nobsv2.security;

import com.example.nobsv2.mappings.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerUserController {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserRepository customUserRepository;

    public CustomerUserController(PasswordEncoder passwordEncoder, CustomUserRepository customUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser customUser) {
        Optional<CustomUser> optionalCustomUser = customUserRepository.findById(customUser.getUsername());
        if (!optionalCustomUser.isPresent()) {
            customUserRepository.save(new CustomUser(customUser.getUsername(), passwordEncoder.encode(customUser.getPassword())));
            return ResponseEntity.ok("Succes");
        }

        return ResponseEntity.badRequest().body("Failure");
    }

}
