package keno.blogProjectDb.webapi.tag;

import keno.blogProjectDb.webapi.support.QueryDto;
import lombok.Data;

@Data
public class HashTagListQueryDto extends QueryDto {
    //private Integer page = 0;
    //private Integer numHashTags = 10;
    private String sort = "new";
    private String query = "";
}
