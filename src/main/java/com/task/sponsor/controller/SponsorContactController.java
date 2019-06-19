package com.task.sponsor.controller;

import com.task.sponsor.domain.SponsorContact;
import com.task.sponsor.service.SponsorContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SponsorContactController {

    private final SponsorContactService service;

    public SponsorContactController(SponsorContactService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "/{sponsor_id}/{contact_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Associate contact")
    public SponsorContact associateContact(@PathVariable(value = "sponsor_id") Long sponsorId, @PathVariable(value = "contact_id") Long contactId) {
//        return service.associateContact(sponsorId, contactId);
        return null;
    }
}
