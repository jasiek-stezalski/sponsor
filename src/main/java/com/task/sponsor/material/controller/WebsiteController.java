package com.task.sponsor.material.controller;

import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.WebsiteDto;
import com.task.sponsor.material.service.WebsiteService;
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
@RequestMapping("/material/website")
public class WebsiteController {

    private final WebsiteService service;

    public WebsiteController(WebsiteService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create website")
    public WebsiteDto createWebsite(@RequestBody Website website) {
        return service.save(website);
    }

    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update website")
    public WebsiteDto updateWebsite(@RequestBody Website website) {
        return service.update(website);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get website by id")
    public WebsiteDto getWebsiteById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all websites")
    public List<WebsiteDto> getAllWebsite() {
        return service.findAll();
    }
}
