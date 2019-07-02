package com.task.sponsor.controller;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.dto.ContactSummaryDto;
import com.task.sponsor.service.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create contact")
    public ContactDto createContact(@RequestBody Contact contact) {
        return service.save(contact);
    }

    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update contact")
    public ContactDto updateContact(@RequestBody Contact contact) {
        return service.update(contact);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get contact by id")
    public ContactDto getContactById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all contacts")
    public List<ContactSummaryDto> getAllContacts() {
        return service.findAll();
    }
}
