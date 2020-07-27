package com.mentor.budget.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
/**
 * SpendRequst ::  request object for spend request
 */
public class SpendRequest {
    @NotNull(message = "teammateId must not be null")
    @DecimalMin("1.00")
    private Float teammateId;
    @NotNull(message = "amount must not be null")
    @DecimalMin("1.00")
    private Float amount;
    @NotBlank(message = "description must not be null")
    private String description;
}
