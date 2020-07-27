package com.mentor.budget.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
/**
 * SpendResponse ::  Contains the response status Approved/Denied
 */
public class SpendResponse {
    private String requestStatus;
}
