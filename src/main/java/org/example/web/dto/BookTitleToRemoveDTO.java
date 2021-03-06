package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTitleToRemoveDTO {

    @NotNull(message = "Must be not null")
    @Size(min = 2, max = 30,message = "Must be between 2 and 20")
    private String bookTitleToRemove;

}
