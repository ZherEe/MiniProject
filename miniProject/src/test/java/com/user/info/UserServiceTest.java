package com.user.info;

import com.user.info.project.UserInfo;
import com.user.info.project.UserInfoRepo;
import com.user.info.project.UserService;
import com.user.info.project.UsersResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserInfoRepo userInfoRepo;

    @Test
    public void getAllUsersTest(){
        UserInfo user1 = new UserInfo();
        user1.setName("me1");
        user1.setSalary(1000.0);
        UserInfo user2 = new UserInfo();
        user2.setName("me2");
        user2.setSalary(2000.0);
        List<UserInfo> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userInfoRepo.getUserInfo(0.0,4000.0,0,null)).thenReturn(users);

        try{
            UsersResults resuls = userService.getAllUsers(0.0,4000.0,0,null,null);

            assertEquals(3, resuls.getResults().size());
            verify(userInfoRepo,times(1)).getUserInfo(0.0,4000.0,0,null);
        }
        catch(Exception e){

        }

    }

}
