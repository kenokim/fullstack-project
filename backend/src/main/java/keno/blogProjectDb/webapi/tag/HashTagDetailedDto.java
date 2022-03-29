package keno.blogProjectDb.webapi.tag;

import lombok.Data;

@Data
public class HashTagDetailedDto {
    private Long id;
    private String tagName;
    private String explanation;
    private Integer numArticles;
}
