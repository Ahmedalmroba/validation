package com.example.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotNull(message = "id should be not empty")
    @Min(3)
    @Max(30)
    private int id;

    @NotEmpty(message = "title should be not empty")
    @Size(min = 9,  message = "Length more than 8")
    private String title;

    @NotEmpty(message = "description should be not empty")
    @Size(min = 16,  message = "Length more than 15")
    private String description;

    @NotEmpty(message = "status should be not empty")
    @Pattern(regexp = "e Not Started|r in Progress|Completed only  ",message = "status must be Not Started or in Progress or Completed only ")
    private String status;

    @NotEmpty(message = "companyName should be not empty")
    @Size(min = 7,  message = "Length more than 15")
    private String companyName;
}