package com.icaetano.beerprint.security.utils;

public class Constant {

    public enum UserRole {
        ROLE_SUPER_ADMIN(UserAuthorities.SUPER_ADMIN),
        ROLE_ADMIN(UserAuthorities.ADMIN),
        ROLE_USER(UserAuthorities.USER);

        public final String value;

        UserRole(String value) {
            this.value = value;
        }

        public static class UserAuthorities {
            private UserAuthorities() {
            }

            public static final String USER = "ROLE_USER";

            public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";

            public static final String ADMIN = "ROLE_ADMIN";
        }
    }
}