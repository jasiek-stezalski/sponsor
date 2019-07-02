package com.task.sponsor.controller;

import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
import com.task.sponsor.dto.SponsorSummaryDto;
import com.task.sponsor.service.SponsorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {

    private final SponsorService service;

    public SponsorController(SponsorService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create sponsor")
    public SponsorDto createSponsor(@RequestBody Sponsor sponsor) {
        return service.save(sponsor);
    }

    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update sponsor")
    public SponsorDto updateSponsor(@RequestBody Sponsor sponsor) {
        return service.update(sponsor);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get sponsor by id")
    public SponsorDto getSponsorById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all sponsors")
    public List<SponsorSummaryDto> getAllSponsors() {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deactivate sponsor")
    public void deactivateSponsor(@PathVariable Long id) {
        service.deactivate(id);
    }
}
