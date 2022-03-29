package keno.blogProjectDb.support.stackdatadto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerStackDto {
    private OwnerStackDto owner;
    private List<String> tags = new ArrayList<>();
    private List<CommentStackDto> comments = new ArrayList<>();
    private boolean is_accepted;
    private int score;
    private Long last_activity_date;
    private Long creation_date;
    private Long answer_id;
    private Long question_id;
    private String link;
    private String title;
    private String body;

}
