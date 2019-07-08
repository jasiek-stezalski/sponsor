package com.task.sponsor.material.converter;

import com.task.sponsor.exception.DataConflictException;
import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.domain.Material;
import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.ConferenceCallDto;
import com.task.sponsor.material.dto.FileDto;
import com.task.sponsor.material.dto.MaterialDto;
import com.task.sponsor.material.dto.WebsiteDto;
import org.springframework.stereotype.Component;

@Component
public class MaterialConverter {

    public MaterialDto convert(Material material) {
        if (material instanceof File) {
            return new FileDto((File) material);
        }
        if (material instanceof Website) {
            return new WebsiteDto((Website) material);
        }
        if (material instanceof ConferenceCall) {
            return new ConferenceCallDto((ConferenceCall) material);
        }
        throw new DataConflictException("Wrong type of object with id:  " + material.getId());
    }
}
