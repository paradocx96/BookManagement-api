package com.paradocx.service.adapter;

import com.paradocx.dto.MessageDto;
import com.paradocx.dto.user.UserSignInDto;
import com.paradocx.dto.user.UserSignUpDto;
import com.paradocx.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserAdapter {
    ResponseEntity<MessageDto> signIn(UserSignInDto userSignInDto);

    ResponseEntity<MessageDto> signUp(UserSignUpDto userSignUpDto);

    ResponseEntity<MessageDto> getUserByUsername(String username);

    ResponseEntity<MessageDto> updateUser(User user);

    ResponseEntity<MessageDto> deleteUser(long id);

    List<User> getAllUsers();
}
