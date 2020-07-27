package com.mentor.budget.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@ConfigurationProperties(prefix = "project")
/**
 * Project :: Configuration class, values will be updated from command line args or default values will be consid
 */
public class Project {
    private String name;
    private Float budget;
}

