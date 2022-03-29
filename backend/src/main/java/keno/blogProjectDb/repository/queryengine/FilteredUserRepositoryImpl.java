package keno.blogProjectDb.repository.queryengine;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.domain.QHashTag;
//import keno.blogProjectDb.domain.QUser;
import keno.blogProjectDb.domain.QUser;
import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.webapi.user.UserListQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class FilteredUserRepositoryImpl implements FilteredUserRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<User> findAllByQueryDto(UserListQueryDto queryDto) {
        Pageable pageable = queryDto.getPageRequest();
        //QHashTag hashTag = QHashTag.hashTag;
        QUser user = QUser.user;
        JPQLQuery<User> q = query.selectFrom(user);
        switch (queryDto.getSort()) {
            case "reputation":
                q.orderBy(user.id.desc());
                break;
            case "votes":
                q.orderBy(user.id.desc());
                break;
            case "edits":
                q.orderBy(user.id.desc());
                break;
            case "writes":
                q.orderBy(user.articles.size().desc());
                break;
            case "answers":
                q.orderBy(user.answers.size().desc());
                break;
            default:
                q.orderBy(user.id.desc());
                break;
        }

        q.where(user.loginId.contains(queryDto.getQuery()));


        q.offset(pageable.getOffset());
        q.limit(pageable.getPageSize());

        List<User> res = q.fetch();
        Long count = query.selectFrom(user)
                .stream().count();
        return new PageImpl<>(res, pageable, count);


    }




}
