package keno.blogProjectDb.webapi.support;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public abstract class QueryDto {
    Integer page = 0;
    Integer numItems = 10;

    public PageRequest getPageRequest() {
       return PageRequest.of(page, numItems);
    }
}
