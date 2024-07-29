package com.example.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    @NotNull(message = "id should be not empty")
    @Min(3)
    private int id;
    @NotEmpty(message = "description should be not empty")
    @Size(min = 16,  message = "Length more than 15")
    private String description;
    @NotNull(message = "capacity should be not empty")
    @Min(26)
    private int capacity;


}