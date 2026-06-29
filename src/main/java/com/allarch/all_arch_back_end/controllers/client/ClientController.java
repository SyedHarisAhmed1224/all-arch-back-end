package com.allarch.all_arch_back_end.controllers.client;

import com.allarch.all_arch_back_end.models.client.NewResearchContractRequest;
import com.allarch.all_arch_back_end.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/allarch/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/get-services", method = GET)
    public ResponseEntity getClientServices() {
        return clientService.getClientServices();
    }

    @RequestMapping(value = "/insert-new-research-contract", method = POST)
    public ResponseEntity insertNewResearchContract(@RequestBody NewResearchContractRequest contractRequest) {
        return clientService.insertNewResearchContract(contractRequest);
    }

    @RequestMapping(value = "/get-contract-info", method = GET)
    public ResponseEntity getContractInfo(@RequestParam("contract_id") int contractId) {
        return clientService.getContractInfo(contractId);
    }
}
