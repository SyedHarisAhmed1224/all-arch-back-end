package com.allarch.all_arch_back_end.models.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class ViewResearchContractResponse {
    private int serviceCount;
    private int topicStatus;
    private int protocolStatus;
    private int dataStatus;
    private int thesisStatus;
    private int presentationStatus;

    private double totalAmount;
    private double advanceAmount;
    private double topicAmount;
    private double protocolAmount;
    private double dataAmount;
    private double thesisAmount;
    private double presentationAmount;

    private String responseTime;
    private String contractStatus;
    private String contractTimelineStatus;
}
