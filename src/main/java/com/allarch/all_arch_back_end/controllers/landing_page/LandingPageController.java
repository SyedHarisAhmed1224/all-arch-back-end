package com.allarch.all_arch_back_end.controllers.landing_page;

import com.allarch.all_arch_back_end.services.landing_page.LandingPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/allarch/landing-page")
public class LandingPageController {

    @Autowired
    private LandingPageService landingPageService;

    @RequestMapping(value = "/get-interest-fields", method = GET)
    public ResponseEntity getInterestFields() {
        return landingPageService.getInterestFields();
    }

}
