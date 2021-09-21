package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.InternalServerError;
import com.example.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers()
    {
        log.info("fetching all users");
        return userRepository.findAll();
    }
    public User saveUser(User user)
    {
        log.info("saving a user");
        return userRepository.save(user);
    }
    public Optional<User> findUserById(Long id)
    {
        log.info("finding a user by id: {}", id);
       return  userRepository.findById(id);
    }
    public void deleteUser(Long id)
    {
        log.info("deleting a user by {}",id);

        User user = userRepository.findUserById(id);
        if(user == null)
        {
            throw new BadRequest();
        }

        try {
            userRepository.deleteById(id);
        }
        catch (Exception ex)
        {
            throw new InternalServerError();
        }

    }
    public User updateUser(User user) {
        log.info("updating a user");
        try {
            userRepository.save(user);
        } catch (Exception ex)
        {
            throw new InternalServerError();
        }
        return user;
    }

    public User findUserByUsername(String username)
    {
        User user;
        try
        {
            user = userRepository.findUserByUsername(username);
        }
        catch (Exception exception)
        {
            throw new InternalServerError();
        }
        return user;
    }

}
