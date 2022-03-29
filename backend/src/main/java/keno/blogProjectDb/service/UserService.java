package keno.blogProjectDb.service;

import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.exception.DuplicateRegisterException;
import keno.blogProjectDb.exception.LoginFailureException;
import keno.blogProjectDb.repository.UserRepository;
import keno.blogProjectDb.webapi.user.UserListQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId);
        if (user == null) throw new LoginFailureException("아이디 정보가 없습니다.");
        if (user.getPassword().equals(password)) return true;
        return false;
    }

    /**
     * Register a user with loginId, password.
     * @param loginId
     * @param password
     * @return true on success, false on unsuccess
     */
    public User register(String loginId, String password) {
        User dupCheck = userRepository.findByLoginId(loginId);
        if (dupCheck != null) throw new DuplicateRegisterException("이미 존재하는 아이디입니다.");
        User user = userRepository.save(new User(loginId, password));
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsers(UserListQueryDto queryDto) {
        //Pageable pageable = PageRequest.of(queryDto.getPage(), queryDto.getNumUsers());
        Pageable pageable = queryDto.getPageRequest();
        return userRepository.findAll(pageable);
    }

    public Page<User> getAllByQueryDto(UserListQueryDto queryDto) {
        return userRepository.findAllByQueryDto(queryDto);
    }

    public User getByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    public Long getNumUsers() {
        return userRepository.count();
    }
}
