package com.allarch.all_arch_back_end.utils;

public class Scripts {
    public static String registerQuery() {
        return """
                    INSERT Users(EmailAddress, Password, FirstName, LastName, InterestFieldID)
                    VALUES (?, ?, ?, ?, ?)
                """;
    }

    public static String getUserIDQuery() {
        return """
                    SELECT UserID FROM Users WHERE EmailAddress = ?;
                """;
    }

    public static String getUpdateUserSessionQuery() {
        return """
                    CALL SaveUserSession(?, ?)
                """;
    }
}
