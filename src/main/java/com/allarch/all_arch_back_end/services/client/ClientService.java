package com.allarch.all_arch_back_end.services.client;

import com.allarch.all_arch_back_end.models.client.ClientServicesResponse;
import com.allarch.all_arch_back_end.models.client.NewResearchContractRequest;
import com.allarch.all_arch_back_end.models.client.ViewResearchContractResponse;
import com.allarch.all_arch_back_end.utils.ApiResponse;
import com.allarch.all_arch_back_end.utils.SQL;
import com.allarch.all_arch_back_end.utils.Scripts;
import com.allarch.all_arch_back_end.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {

    @Autowired
    private SQL sql;

    public ResponseEntity getClientServices() {
        var query = Scripts.getClientServicesQuery();

        var res = sql.execute(query);

        if (res == null || res.size() <= 0) {
            return ApiResponse.serverError("Failed to fetch services, contact support!");
        }

        var services = new ArrayList<ClientServicesResponse>();

        for (var data : res) {
            services.add(new ClientServicesResponse(
                    Integer.parseInt(data.get("ClientServiceID").toString()),
                    data.get("Icon").toString(),
                    data.get("ServiceName").toString(),
                    data.get("Description").toString(),
                    data.get("TypeName").toString(),
                    Double.parseDouble(data.get("Amount").toString())
            ));
        }

        return ApiResponse.success(services);
    }

    public ResponseEntity insertNewResearchContract(NewResearchContractRequest contractRequest) {
        try {
            var userId = SecurityUtils.getCurrentUserId();

            var query = Scripts.insertNewResearchContractQuery();

            var res = sql.execute(
                    query,
                    userId,
                    contractRequest.getFullName(),
                    contractRequest.getDigitalSignature(),
                    contractRequest.getResearchSupportAgreement(),
                    contractRequest.getPersonalInfoFullName(),
                    contractRequest.getQualificationID(),
                    contractRequest.getFieldDescription(),
                    contractRequest.getHasTopic(),
                    contractRequest.getResearchDescription(),
                    contractRequest.getHasProtocol(),
                    contractRequest.getProtocolFile(),
                    contractRequest.getHasData(),
                    contractRequest.getDataFile(),
                    contractRequest.getWrittenOutputTypeID(),
                    contractRequest.getTargetJournal(),
                    contractRequest.getHasPresentation()
            );

            if (res == null || res.size() <= 0) {
                return ApiResponse.serverError("Failed to insert new contract, contact support!");
            }

            var statusCode = Integer.parseInt(res.get(0).get("StatusCode").toString());

            if (statusCode != -1) {
                return ApiResponse.success(statusCode);
            }

            return ApiResponse.badRequest("User ID does not exist!");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.serverError("Server Error, contact support!");
        }
    }

    public ResponseEntity getContractInfo(int contractId) {
        try {
            var userId = SecurityUtils.getCurrentUserId();

            var query = Scripts.getContractInfoQuery();

            var res = sql.execute(query, userId, contractId);

            if (res == null || res.size() <= 0) {
                return ApiResponse.serverError("Failed to fetch contract info, contact support!");
            }

            return ApiResponse.success(new ViewResearchContractResponse(
                    Integer.parseInt(res.get(0).get("ServiceCount").toString().trim()),
                    Integer.parseInt(res.get(0).get("TopicStatus").toString().trim()),
                    Integer.parseInt(res.get(0).get("ProtocolStatus").toString().trim()),
                    Integer.parseInt(res.get(0).get("DataStatus").toString().trim()),
                    Integer.parseInt(res.get(0).get("ThesisStatus").toString().trim()),
                    Integer.parseInt(res.get(0).get("PresentationStatus").toString().trim()),

                    Double.parseDouble(res.get(0).get("TotalAmount").toString().trim()),
                    Double.parseDouble(res.get(0).get("AdvanceAmount").toString().trim()),
                    Double.parseDouble(res.get(0).get("TopicAmount").toString().trim()),
                    Double.parseDouble(res.get(0).get("ProtocolAmount").toString().trim()),
                    Double.parseDouble(res.get(0).get("DataAmount").toString().trim()),
                    Double.parseDouble(res.get(0).get("ThesisAmount").toString().trim()),
                    Double.parseDouble(res.get(0).get("PresentationAmount").toString().trim()),

                    res.get(0).get("ResponseTime") == null ? "" : res.get(0).get("ResponseTime").toString().trim(),
                    res.get(0).get("ContractStatus") == null ? "" : res.get(0).get("ContractStatus").toString().trim(),
                    res.get(0).get("ContractTimelineStatus") == null ? "" : res.get(0).get("ContractTimelineStatus").toString().trim()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.serverError("Server Error, contact support!");
        }
    }
}
