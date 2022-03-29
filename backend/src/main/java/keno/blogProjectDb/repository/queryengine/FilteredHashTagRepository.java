package keno.blogProjectDb.repository.queryengine;

import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.webapi.tag.HashTagListQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface FilteredHashTagRepository {
    Page<HashTag> findAllByQueryDto(HashTagListQueryDto queryDto);
}
