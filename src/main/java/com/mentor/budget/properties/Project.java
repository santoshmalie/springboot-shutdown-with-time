package com.mentor.budget.properties;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix="project")
public class Project {
    private String name;
    private Float budget;
}
