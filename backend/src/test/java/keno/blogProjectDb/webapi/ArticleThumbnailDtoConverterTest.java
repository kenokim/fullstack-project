package keno.blogProjectDb.webapi;

import keno.blogProjectDb.webapi.article.ArticleDtoConverter;
import keno.blogProjectDb.webapi.article.ArticleForm;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ArticleThumbnailDtoConverterTest {

    ArticleDtoConverter converter = new ArticleDtoConverter();

    @Test
    public void dateTimeConversionTest() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(converter.localDateTimeToString(dateTime));
    }

    @Test
    public void articleFormTest() {
        ArticleForm form = new ArticleForm();
        form.setTags("JAVA,C++ C#");
        form.getTagList().stream().forEach(t -> {
            System.out.println(t);
        });
    }
}