package com.ism.controllers;

import com.ism.models.User;
import com.ism.services.AuthService;
import com.ism.utils.ConsoleUtils;
import com.ism.utils.SessionManager;

public class AuthController {
    private AuthService authService = new AuthService();

    public boolean login(String email, String password) {
        User user = authService.login(email, password);
        if (user != null) {
            SessionManager.setCurrentUser(user);
            return true;
        }
        return false;
    }

    public void logout() {
        SessionManager.logout();
    }
}