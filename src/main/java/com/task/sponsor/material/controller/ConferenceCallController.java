package com.task.sponsor.material.controller;

import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.dto.ConferenceCallDto;
import com.task.sponsor.material.service.ConferenceCallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/material/conference_call")
public class ConferenceCallController {

    private final ConferenceCallService service;

    public ConferenceCallController(ConferenceCallService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create conferenceCall")
    public ConferenceCallDto createConferenceCall(@RequestBody ConferenceCall conferenceCall) {
        return service.save(conferenceCall);
    }

    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update conferenceCall")
    public ConferenceCallDto updateConferenceCall(@RequestBody ConferenceCall conferenceCall) {
        return service.update(conferenceCall);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get conferenceCall by id")
    public ConferenceCallDto getConferenceCallById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all conferenceCalls")
    public List<ConferenceCallDto> getAllConferenceCalls() {
        return service.findAll();
    }
}