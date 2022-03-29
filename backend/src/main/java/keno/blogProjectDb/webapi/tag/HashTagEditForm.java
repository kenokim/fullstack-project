package keno.blogProjectDb.webapi.tag;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class HashTagEditForm {
    @NotEmpty
    private String content;
}
