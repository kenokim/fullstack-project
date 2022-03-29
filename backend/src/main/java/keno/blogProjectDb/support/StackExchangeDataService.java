package keno.blogProjectDb.support;

import keno.blogProjectDb.domain.*;
import keno.blogProjectDb.repository.*;
import keno.blogProjectDb.support.stackdatadto.AnswerStackDto;
import keno.blogProjectDb.support.stackdatadto.CommentStackDto;
import keno.blogProjectDb.support.stackdatadto.ItemStackDto;
import keno.blogProjectDb.support.stackdatadto.OwnerStackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StackExchangeDataService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;
    private final HashTagRepository hashTagRepository;

    private int idx = 0;
    private ArrayList<User> owners = new ArrayList<>();

    @Transactional
    public void store(ItemStackDto itemDto) {
        // 1. save owners
        saveOwner(itemDto.getOwner());
        for (AnswerStackDto a : itemDto.getAnswers()) {
            saveOwner(a.getOwner());
            for (CommentStackDto c : a.getComments()) {
                saveOwner(c.getOwner());
            }
        }
        for (CommentStackDto c : itemDto.getComments()) {
            saveOwner(c.getOwner());
        }


        // 2. save tags, article
        List<HashTag> tags = new ArrayList<>();
        itemDto.getTags().stream().forEach(t -> {
            HashTag hashTag = HashTag.createHashTag(t);
            hashTagRepository.save(hashTag);
            tags.add(hashTag);
        });

        Article article = Article.createArticle(
                nextOwner(),
                itemDto.getTitle(),
                //LocalDateTime.ofEpochSecond(itemDto.getCreation_date(), 0, ZoneOffset.UTC),
                toTime(itemDto.getCreation_date()),
                itemDto.getBody(),
                tags
        );

        articleRepository.save(article);

        // 3. save answers 4. save comments
        for (AnswerStackDto a : itemDto.getAnswers()) {
            Answer answer = article.addAnswer2(nextOwner(), toTime(a.getCreation_date()), a.getBody());
            answerRepository.save(answer);
            for (CommentStackDto ac : a.getComments()) {
                Comment acs = answer.addComment2(nextOwner(), LocalDateTime.now(), ac.getBody());
                commentRepository.save(acs);
            }
        }
        for (CommentStackDto c : itemDto.getComments()) {
            Comment ac = article.addComment2(nextOwner(), LocalDateTime.now(), c.getBody());
            commentRepository.save(ac);
        }
    }

    @Transactional
    public void saveOwner(OwnerStackDto dto) {
        User owner = User.createUser(dto.getDisplay_name(), "test!");
        userRepository.save(owner);
        owners.add(owner);
    }

    public User nextOwner() {
        return owners.get(idx++);
    }

    public LocalDateTime toTime(Long epochTime) {
        return LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC);
    }
}
