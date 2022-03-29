package keno.blogProjectDb.service;

import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.repository.HashTagRepository;
import keno.blogProjectDb.webapi.tag.HashTagListQueryDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    /*public Page<HashTag> getAllHashTags(HashTagListQueryDto queryDto) {
        Pageable pageable = PageRequest.of(queryDto.getPage(), queryDto.getNumHashTags());
        return hashTagRepository.findAll(pageable);
    }*/

    /*public Page<HashTag> getAllHashTagsByQueryDto(HashTagListQueryDto queryDto) {
        //Pageable pageable = PageRequest.of(queryDto.getPage(), queryDto.getNumHashTags());
        Pageable pageable = queryDto.getPageRequest();
        String sort = queryDto.getSort();
        if (sort.equals("popular")) {
            System.out.println("popular");
            //return hashTagRepository.findAllByOrderByNumArticlesDesc(pageable);
        }
        else if (sort.equals("name")) {
            System.out.println("name");
            return hashTagRepository.findByTagNameContainsOrderByTagNameAsc( pageable,queryDto.getQuery());
            //return hashTagRepository.findAllByOrderByTagNameAsc(pageable);
        }
        System.out.println("new");
        //return hashTagRepository.findAll(pageable);
        return hashTagRepository.findByTagNameContains(pageable, queryDto.getQuery());
        //return hashTagRepository.findAllByQueryDto(queryDto);
    }*/

    public Page<HashTag> getAllHashTagsByQueryDto(HashTagListQueryDto queryDto) {
        return hashTagRepository.findAllByQueryDto(queryDto);
    }

    public HashTag getOneByTagName(String tagName) {
        HashTag hashTag = hashTagRepository.findHashTagByTagName(tagName);
        if (hashTag == null) throw new NoSuchElementException("해쉬태그가 없습니다.");
        else return hashTag;
    }


    @Transactional
    public HashTag getOneOrCreate(String tagName) {
        HashTag hashTag = hashTagRepository.findHashTagByTagName(tagName);
        if (hashTag != null) return hashTag;
        HashTag newHashTag = HashTag.createHashTag(tagName);
        hashTagRepository.save(newHashTag);
        return newHashTag;
    }

    public List<HashTag> stringListToHashTagList(List<String> tags) {
        List<HashTag> hashTagList = new ArrayList<>();
        tags.stream().forEach(t -> {
            HashTag hashTag = getOneOrCreate(t);
            hashTagList.add(hashTag);
        });
        return hashTagList;
    }

    @Transactional
    public void editTagExplanation(String tagName, String content) {
        HashTag hashTag = hashTagRepository.findHashTagByTagName(tagName);
        hashTag.changeExplanation(content);
    }
}
