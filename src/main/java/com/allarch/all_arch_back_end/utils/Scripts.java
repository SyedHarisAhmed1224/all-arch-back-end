package com.allarch.all_arch_back_end.utils;

public class Scripts {
    public static String registerQuery() {
        return """
                    EXEC RegisterUser ?, ?, ?, ?, ?
                """;
    }

    public static String getUserIDQuery() {
        return """
                    SELECT UserID FROM Users WHERE EmailAddress = ?;
                """;
    }

    public static String getUpdateUserSessionQuery() {
        return """
                    EXEC SaveUserSession ?, ?
                """;
    }

    public static String getLoginQuery() {
        return """
                            SELECT 1 FROM Users WHERE EmailAddress = ? AND Password = ?;
                """;
    }

    public static String getInterestFieldsQuery() {
        return "SELECT FieldID, FieldName FROM InterestField";
    }

    public static String getClientServicesQuery() {
        return """
                SELECT a.ClientServiceID, a.Icon, a.ServiceName, a.Description, b.TypeName, a.Amount
                FROM   ClientServices a, ClientServiceTypes b
                WHERE  a.Type   = b.ClientServiceTypeID
                AND    a.Status = 0
                """;
    }

    public static String insertNewResearchContractQuery() {
        return """
                EXEC InsertResearchContract
                     @UserID = ?,
                     @FullName = ?,
                     @DigitalSignature = ?,
                     @ResearchSupportAgreement = ?,
                     @PersonalInfoFullName = ?,
                     @QualificationID = ?,
                     @FieldDescription = ?,
                     @HasTopic = ?,
                     @ResearchDescription = ?,
                     @HasProtocol = ?,
                     @ProtocolFile = ?,
                     @HasData = ?,
                     @DataFile = ?,
                     @WrittenOutputTypeID = ?,
                     @TargetJournal = ?,
                     @HasPressentation = ?
                """;
    }

    public static String getContractInfoQuery() {
        return """
                EXEC GetClientContractInfo @UserID = ?, @ContractID = ?
                """;
    }
}
