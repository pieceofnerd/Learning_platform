package com.sytoss.controller;

import com.sytoss.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;


}