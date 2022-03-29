package keno.blogProjectDb.webapi.article;

import keno.blogProjectDb.webapi.support.TagSeperator;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ArticleForm {

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Content must not be empty")
    private String content;

    private LocalDateTime dateTime;

    /**
     * TO FIX:
     * "," " " consecutiveness must return invalidity
     */
    @TagSeperator
    private String tags;

    public List<String> getTagList() {
        List<String> tagList = new ArrayList<>();
        Arrays.stream(this.tags.split(" |,")).distinct().forEach(t -> { tagList.add(t); });
        return tagList;
    }
}
