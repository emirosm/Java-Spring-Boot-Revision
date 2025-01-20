package com.example.nobsv2.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class SecurityController {

    @GetMapping("/open")
    public String open() {
        return "open";
    }

    @GetMapping("/closed")
    public String closed() {
        return "closed";
    }

    @PreAuthorize("hasRole('superuser')")
    @GetMapping("/special")
    public String special() {
        return "special";
    }

    @PreAuthorize("hasRole('basicuser')")
    @GetMapping("/basic")
    public String basic() {
        return "basic";
    }
}
