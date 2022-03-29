package keno.blogProjectDb.webapi.answer;

import lombok.Data;

@Data
public class AnswerForm {
    private Long articleId;
    private String content;
}
