package com.allarch.all_arch_back_end.models.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class NewResearchContractRequest {
    private int researchSupportAgreement;
    private int qualificationID;
    private int hasTopic;
    private int hasProtocol;
    private int hasData;
    private int writtenOutputTypeID;
    private int hasPresentation;

    private String fullName;
    private String digitalSignature;
    private String personalInfoFullName;
    private String fieldDescription;
    private String researchDescription;
    private String targetJournal;

    private byte[] protocolFile;
    private byte[] dataFile;
}
