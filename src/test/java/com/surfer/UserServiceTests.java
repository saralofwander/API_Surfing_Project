package com.surfer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.entities.Beach;
import com.entities.User;
import com.repositories.BeachSqlRepository;
import com.repositories.PostSqlRepository;
import com.repositories.UserSqlRepository;
import com.requests.AddPostRequest;
import com.requests.AddUserRequest;
import com.requests.UpdateUserRequest;
import com.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTests {
    
    private UserService sut;

    private  UserSqlRepository userRepo;
    private  BeachSqlRepository beachRepo;
    private  PostSqlRepository postRepo;


    @BeforeEach
    void initTests(){
        this.userRepo = Mockito.mock(UserSqlRepository.class);
        this.beachRepo = Mockito.mock(BeachSqlRepository.class);
        this.postRepo = Mockito.mock(PostSqlRepository.class);

        this.sut = new UserService();
    }

    @Test
    void UserService_updateUser_Success(){
        // ARRANGE
        var user = new User();
        user.setId(1L);
        user.setFirstName("Tester");
        user.setLastName("Testersson");
        user.setCreatedAt(new Date());
        Optional<User> studentMock = Optional.of((User) user);

        when(userRepo.findById(anyLong())).thenReturn(studentMock);
        when(userRepo.save(any(User.class))).thenReturn(user);

        var updateRequest = new UpdateUserRequest();
        updateRequest.setLastName("Andersson");

        // ACT
        var result = sut.updateUser(1L, updateRequest);

        // ASSERT
        assertEquals(updateRequest.getLastName(), result.getLastName());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    void UserService_addUser_Success(){

        when(beachRepo.save(any(Beach.class))).thenAnswer(i -> i.getArguments()[0]);
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        var addRequest = new AddUserRequest();
        addRequest.setFirstName("Tester");
        addRequest.setLastName("Testersson");
        addRequest.setLocation("HappyCity");
        addRequest.setBeachName("HappyStreet");
        addRequest.setPosts(new ArrayList<AddPostRequest>());

        // ACT
        var result = sut.addUser(addRequest);

        // ASSERT
        assertEquals(addRequest.getFirstName(), result.getFirstName());
        assertEquals(addRequest.getLastName(), result.getLastName(), "Lastname did not match");
        assertNotNull(result.getBeach());
        assertEquals(addRequest.getLocation(), result.getBeach().getLocation());
        assertEquals(addRequest.getBeachName(), result.getBeach().getBeachName());
        assertNotNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

}