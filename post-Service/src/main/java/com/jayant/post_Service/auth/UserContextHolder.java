package com.jayant.post_Service.auth;

public class UserContextHolder {

    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();          // Persists only till the thread is valid , it does not share the info to other threads

    //It creates a variable that is unique per thread — each request gets its own isolated copy of currentUserId.
    // No two requests can read or overwrite each other's value

    public static Long getCurrentUserId() {
        return currentUserId.get();
    }

    // only classes in the same package can write/clear the value — external code can only read it via getCurrentUserId()

    static void setCurrentUserId(Long userId) {
        currentUserId.set(userId);
    }

    static void clear() {
        currentUserId.remove();
    }
}
