package keno.blogProjectDb.webapi.user;

import keno.blogProjectDb.webapi.support.QueryDto;
import lombok.Data;
import lombok.ToString;

@Data @ToString
public class UserListQueryDto extends QueryDto {
    //private Integer page = 0;
    //private Integer numUsers = 10;
    private String query = "";
    private String sort = "";
}
