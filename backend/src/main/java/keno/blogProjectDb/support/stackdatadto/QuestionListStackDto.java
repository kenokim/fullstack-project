package keno.blogProjectDb.support.stackdatadto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListStackDto implements Serializable {
    private List<ItemStackDto> items = new ArrayList<>();
    private boolean has_more;
    private int backoff;
    private int quota_max;
    private int quota_remaining;
    private int page;
    private int page_size;
    private Long total;
    private String type;

}