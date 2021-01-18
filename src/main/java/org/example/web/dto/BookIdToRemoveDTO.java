package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookIdToRemoveDTO {

    @NotNull(message = "Must be not null")
    private Integer bookIdToRemove;

}
