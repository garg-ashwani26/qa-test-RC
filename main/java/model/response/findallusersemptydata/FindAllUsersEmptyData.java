package model.response.findallusersemptydata;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindAllUsersEmptyData {

    public List<Link> links = null;
    public List<Content> content = null;
    public Page page;
}
