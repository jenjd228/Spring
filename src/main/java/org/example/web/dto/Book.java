package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 30,message = "Must be between 2 and 30")
    private String author;

    @NotNull
    @Size(min = 2, max = 30,message = "Must be between 2 and 20")
    private String title;

    @NotNull(message = "Must be not null")
    @Min(value = 1,message = "Must be greater than or equal to 1")
    @Max(value = 9999,message = "Must be less than or equal to 1")
    private Integer size;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
