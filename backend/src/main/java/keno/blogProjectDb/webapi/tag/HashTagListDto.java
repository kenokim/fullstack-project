package keno.blogProjectDb.webapi.tag;

import keno.blogProjectDb.domain.HashTag;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class HashTagListDto {
    private Long totalNumTags = 0L;
    private List<HashTagDto> tags = new ArrayList<>();

    public static HashTagListDto createHashTagListDto(Page<HashTag> tags) {
        HashTagListDto dto = new HashTagListDto();
        dto.totalNumTags = tags.getTotalElements();
        tags.stream().forEach(t -> {
           dto.tags.add(HashTagListDto.hashTagToDto(t));
        });
        return dto;
    }

    public static HashTagDto hashTagToDto(HashTag tag) {
        String defaultExplanation = "아직 태그 설명이 작성되지 않았습니다.";
        if (tag.getExplanation() != null) defaultExplanation = tag.getExplanation();
        return HashTagDto.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
                .numArticles(tag.getArticles().size())
                .explanation(defaultExplanation)
                .build();
    }
}
