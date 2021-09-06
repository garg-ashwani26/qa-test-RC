package model.response.findallusersemptydata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    public int size;
    public int totalElements;
    public int totalPages;
    public int number;
}
