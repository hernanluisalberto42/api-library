package com.collage.library.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class utilss {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.printf(passwordEncoder.encode("123"));
    }
}
