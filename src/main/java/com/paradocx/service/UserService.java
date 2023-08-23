package com.paradocx.service;

import com.paradocx.dto.MessageDto;
import com.paradocx.dto.user.UserDto;
import com.paradocx.dto.user.UserSignInDto;
import com.paradocx.dto.user.UserSignUpDto;
import com.paradocx.model.User;
import com.paradocx.repository.IUserRepository;
import com.paradocx.service.adapter.IUserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserAdapter {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<MessageDto> signIn(UserSignInDto userSignInDto) {
        try {
            User user = userRepository.findByUsername(userSignInDto.getUsername());

            if (user == null) {
                return ResponseEntity.ok(new MessageDto("User not found!", null));
            }

            if (user.getPassword().equals(userSignInDto.getPassword())) {
                return ResponseEntity.ok(new MessageDto("User Found!", new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getRole()
                )));
            } else {
                return ResponseEntity.ok(new MessageDto("Invalid Password!", null));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Invalid Credentials!", null));
    }

    @Override
    public ResponseEntity<MessageDto> signUp(UserSignUpDto userSignUpDto) {
        try {
            User existingUsername = userRepository.findByUsername(userSignUpDto.getUsername());

            if (existingUsername != null) {
                return ResponseEntity.ok(new MessageDto("Username already exists!", null));
            }

            User existingEmail = userRepository.findByEmail(userSignUpDto.getEmail());

            if (existingEmail != null) {
                return ResponseEntity.ok(new MessageDto("Email already exists!", null));
            }

            // Instantiate a new User object
            User user = new User();
            user.setName(userSignUpDto.getName());
            user.setUsername(userSignUpDto.getUsername());
            user.setEmail(userSignUpDto.getEmail());
            user.setPassword(userSignUpDto.getPassword());
            user.setRole("USER");

            user = userRepository.save(user);

            return ResponseEntity.ok(new MessageDto("User Registered Successfully!", new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole()
            )));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("User Registration Failed!", null));
    }

    @Override
    public ResponseEntity<MessageDto> getUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);

            if (user == null) {
                return ResponseEntity.ok(new MessageDto("User not found!", null));
            }

            return ResponseEntity.ok(new MessageDto("User Found!", new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole()
            )));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("User not found!", null));
    }

    @Override
    public ResponseEntity<MessageDto> updateUser(User user) {
        try {
            User existingUser = userRepository.findByUsername(user.getUsername());

            if (existingUser == null) {
                return ResponseEntity.ok(new MessageDto("User not found!", null));
            }

            User updatedUser = userRepository.save(user);

            return ResponseEntity.ok(new MessageDto("User Updated Successfully!", new UserDto(
                    updatedUser.getId(),
                    updatedUser.getName(),
                    updatedUser.getEmail(),
                    updatedUser.getUsername(),
                    updatedUser.getRole()
            )));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("User Update Failed!", null));
    }

    @Override
    public ResponseEntity<MessageDto> deleteUser(long id) {
        try {
            User user = userRepository.findById(id).orElse(null);

            if (user == null) {
                return ResponseEntity.ok(new MessageDto("User not found!", null));
            }

            userRepository.deleteById(id);
            return ResponseEntity.ok(new MessageDto("User deleted successfully!", null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("User Deletion Failed!", null));
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
