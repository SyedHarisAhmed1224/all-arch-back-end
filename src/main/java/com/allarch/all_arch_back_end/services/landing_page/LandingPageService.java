package com.allarch.all_arch_back_end.services.landing_page;

import com.allarch.all_arch_back_end.utils.ApiResponse;
import com.allarch.all_arch_back_end.utils.SQL;
import com.allarch.all_arch_back_end.utils.Scripts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LandingPageService {
    @Autowired
    private SQL sql;

    public ResponseEntity getInterestFields() {
        String query = Scripts.getInterestFieldsQuery();

        var res = sql.execute(query);

        if (res != null && res.size() > 0) {
            return ApiResponse.success(res);
        }

        return ApiResponse.serverError("Contact Support");
    }
}
