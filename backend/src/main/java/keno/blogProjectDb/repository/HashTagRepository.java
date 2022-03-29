package keno.blogProjectDb.repository;

import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.repository.queryengine.FilteredHashTagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long>, FilteredHashTagRepository {

    HashTag findHashTagByTagName(String tagName);

    Page<HashTag> findAll(Pageable pageable);

    //@Query("select t from HashTag t join fetch t.articles")
    //Page<HashTag> findAllFetched(Pageable pageable);

    //@Query("select t from HashTag t order by t.articles.size desc")
    //Page<HashTag> findAllByOrderByNumArticlesDesc(Pageable pageable);

    Page<HashTag> findAllByOrderByTagNameAsc(Pageable pageable);

    Page<HashTag> findByTagNameContains(Pageable pageable, String tagName);

    Page<HashTag> findByTagNameContainsOrderByTagNameAsc(Pageable pageable, String tagName);

}
