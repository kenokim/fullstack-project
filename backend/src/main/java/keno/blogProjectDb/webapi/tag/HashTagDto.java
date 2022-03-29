package keno.blogProjectDb.webapi.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HashTagDto {
    private Long id;
    private String tagName;
    private Integer numArticles = 0;
    private String explanation;
}
