package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSizeToRemoveDTO {

    @NotNull(message = "Must be not null")
    @Min(value = 1,message = "Must be greater than or equal to 1")
    @Max(value = 9999,message = "Must be less than or equal to 1")
    private Integer bookSizeToRemove;

}
