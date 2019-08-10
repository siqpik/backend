package com.example.siqpik;

import com.example.siqpik.auth.AuthenticationBean;
import org.springframework.web.bind.annotation.GetMapping;

public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public AuthenticationBean authenticate() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new AuthenticationBean("You are authenticated");
    }
}
