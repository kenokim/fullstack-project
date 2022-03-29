package keno.blogProjectDb.repository.queryengine;

import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.webapi.user.UserListQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface FilteredUserRepository {
    Page<User> findAllByQueryDto(UserListQueryDto queryDto);
}
