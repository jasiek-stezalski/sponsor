package com.task.sponsor.material.controller;

import com.task.sponsor.material.dto.MaterialDto;
import com.task.sponsor.material.service.MaterialService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get material by id")
    public MaterialDto getMaterialById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all materials")
    public List<MaterialDto> getAllMaterials() {
        return service.findAll();
    }

    @ResponseBody
    @PutMapping(value = "/support/{material_id}/{support_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Associate contact")
    public MaterialDto addSupportMaterial(@PathVariable(value = "material_id") Long materialId, @PathVariable(value = "support_id") Long supportId) {
        return service.addSupportMaterial(materialId, supportId);
    }
}
