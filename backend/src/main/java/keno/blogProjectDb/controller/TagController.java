package keno.blogProjectDb.controller;

import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.service.HashTagService;
import keno.blogProjectDb.webapi.tag.HashTagDto;
import keno.blogProjectDb.webapi.tag.HashTagEditForm;
import keno.blogProjectDb.webapi.tag.HashTagListQueryDto;
import keno.blogProjectDb.webapi.tag.HashTagListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TagController {

    private final HashTagService hashTagService;

    @GetMapping("/tags/{tagName}")
    public HashTagDto getHashTagDetailed(@PathVariable("tagName") String tagName) {
        HashTag hashTag = hashTagService.getOneByTagName(tagName);
        return HashTagListDto.hashTagToDto(hashTag);
    }

    @GetMapping("/tags/{tagName}/tooltip")
    public ResponseEntity getHashTagToolTip(@PathVariable("tagName") String tagName) {
        HashTag hashTag = hashTagService.getOneByTagName(tagName);
        String expl = "아직 설명이 작성되지 않았습니다.";
        if (hashTag.getExplanation() != null) expl = hashTag.getExplanation();
        String explaination = "TagName: " + tagName + "\n\nExplanation: " + expl + "\n\n#Questions tagged: " + hashTag.getArticles().size() +"\n\nTo edit this tag, click the 'edit tag' button. \nTo query for questions tagged with this, click this button.";
        ResponseEntity entity = new ResponseEntity(explaination, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/tags")
    public HashTagListDto getAllTags(HashTagListQueryDto queryDto) {
        //Page<HashTag> hashTags = hashTagService.getAllHashTags(queryDto);
        Page<HashTag> hashTags = hashTagService.getAllHashTagsByQueryDto(queryDto);
        return HashTagListDto.createHashTagListDto(hashTags);
    }



    @PostMapping("/tags/{tagName}/edit")
    public ResponseEntity editTag(@PathVariable("tagName") String tagName, @RequestBody HashTagEditForm editForm) {
        String content = editForm.getContent();
        hashTagService.editTagExplanation(tagName, content);
        return new ResponseEntity(HttpStatus.OK);
    }

}
