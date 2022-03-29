package keno.blogProjectDb.repository;

import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.repository.queryengine.FilteredUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, FilteredUserRepository {



    Page<User> findAll(Pageable pageable);

    User findByLoginId(String loginId);

    @Query("select u from User u order by u.articles.size")
    Page<User> findAllOrderByNumArticles(Pageable pageable);

    // 쓴 게시글의 태그 수의 합이 가장 많은 사람?
    // ''         좋아요
}
