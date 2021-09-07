package model.response.findallusers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    private int size;
    private int totalElements;
    private int totalPages;
    private int number;
}
