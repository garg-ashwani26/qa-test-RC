package model.response.findallusersemptydata;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Content {

    public Object rel;
    public boolean collectionValue;
    public String relTargetType;
    public List<Object> value = null;
}
