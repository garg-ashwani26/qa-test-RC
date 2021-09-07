package model.response.findallusers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindAllUsers {

    private List<Link> links = null;
    private List<Content> content = null;
    private Page page;
}
