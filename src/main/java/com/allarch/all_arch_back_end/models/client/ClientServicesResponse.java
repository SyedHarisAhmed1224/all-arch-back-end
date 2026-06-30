package com.allarch.all_arch_back_end.models.client;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Data
public class ClientServicesResponse {
    private int serviceId;
    private String icon;
    private String title;
    private String info;
    private String type;
    private int hasForm;
    private double amount;
}
