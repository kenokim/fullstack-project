package keno.blogProjectDb.webapi.article;

import keno.blogProjectDb.webapi.support.QueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class ArticleListQueryDto extends QueryDto {
    private String tags;
    private String sort;
    private String filters;
    private String query;


    public String getSort() {
        if (this.sort == null) return "new";
        return this.sort;
    }

    public List<String> getTags() {
        if (tags == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(tags.split(" ")));
    }

    public List<String> getFilters() {
        if (filters == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(filters.split(" ")));
    }

    public String getQuery() {
        if (query == null) return "";
        return query;
    }

}
