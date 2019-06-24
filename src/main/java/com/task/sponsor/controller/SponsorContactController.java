package com.task.sponsor.controller;

import com.task.sponsor.dto.SponsorContactDto;
import com.task.sponsor.service.SponsorContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SponsorContactController {

    private final SponsorContactService service;

    public SponsorContactController(SponsorContactService service) {
        this.service = service;
    }

    @ResponseBody
    @PutMapping(value = "/assoc/{sponsor_id}/{contact_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Associate contact")
    public SponsorContactDto associateContact(@PathVariable(value = "sponsor_id") Long sponsorId, @PathVariable(value = "contact_id") Long contactId,
                                              @RequestParam Boolean primary, @RequestParam Boolean secondary) {
        return service.associateContact(sponsorId, contactId, primary, secondary);
    }
}
