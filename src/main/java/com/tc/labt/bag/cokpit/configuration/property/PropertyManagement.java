package com.tc.labt.sgabs.cokpit.configuration.property;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.labt.sgabs.cokpit.util.ManagementUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class PropertyManagement {

    private static final String filename = ManagementUtil.getJiraHome().concat("data/COKPIT/CONFIG/data.conf");
    private static final Logger log = LoggerFactory.getLogger(PropertyManagement.class);

    public static PropertyDTO getProperty(){
        if(!Files.exists(Paths.get(filename)) || !Files.isReadable(Paths.get(filename)))
            return null;
        PropertyDTO propertyDTO = null;
        try {
            ObjectMapper om = new ObjectMapper();
            propertyDTO = om.readValue(new FileReader(filename), PropertyDTO.class);
        } catch (IOException io) {
            log.error(ManagementUtil.ERROR_TITLE+ io.getMessage());
        }
        Objects.requireNonNull(propertyDTO, ManagementUtil.ERROR_TITLE + "The json file Cokpit Config can't be load.");
        Objects.requireNonNull(propertyDTO.property, ManagementUtil.ERROR_TITLE + "The json file Content is badly formated.");
        return propertyDTO;
    }
}
