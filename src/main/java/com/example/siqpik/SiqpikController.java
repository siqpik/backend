package com.example.siqpik;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiqpikController {

    @RequestMapping("/members")
    @ResponseBody
    String home() {
        return "Hello we are Ronn, Pancho, Laura and Yeray. AND WE ARE SIQPIK!!";
    }
}
