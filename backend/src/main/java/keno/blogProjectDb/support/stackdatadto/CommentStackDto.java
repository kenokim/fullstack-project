package keno.blogProjectDb.support.stackdatadto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentStackDto {
    private OwnerStackDto owner;
    private boolean edited;
    private int score;
    private Long creation_date;
    private Long post_id;
    private Long comment_id;
    private String body;

}
