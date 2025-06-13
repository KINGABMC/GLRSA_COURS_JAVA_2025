package com.ism.utils;

import com.ism.models.User;

public class SessionManager {
    private static User currentUser = null;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean hasRole(String role) {
        return currentUser != null && role.equals(currentUser.getRole());
    }

    public static boolean isRP() {
        return hasRole("RP");
    }

    public static boolean isAttache() {
        return hasRole("ATTACHE");
    }

    public static boolean isProfesseur() {
        return hasRole("PROFESSEUR");
    }

    public static boolean isEtudiant() {
        return hasRole("ETUDIANT");
    }
}