package com.shompme.admin;

import com.shompme.admin.user.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateNewUserWithOneRole(){
    Role roleAdmin = testEntityManager.find(Role.class,1);
       User pankaj = new User("pankaj10106@gmail.com","Password","Pankaj","Kumar");
       pankaj.addRole(roleAdmin);
      User userSave =  userRepository.save(pankaj);
      assertThat(userSave.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateNewUserWithTwoRoles(){
        User userRavi = new User("ravi123@gmail.com","Ravi@765","ravi","kumar");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userRavi.addRole(roleEditor);
        userRavi.addRole(roleAssistant);

        User userSave =  userRepository.save(userRavi);
        assertThat(userSave.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAllUser(){
       Iterable<User>listUsers = userRepository.findAll();
       listUsers.forEach(System.out::println);
    }

    @Test
    public void testGetUserById(){
     User user1 = userRepository.findById(1).get();
        System.out.println(user1);
        assertThat(user1).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User user = userRepository.findById(1).get();
        user.setEnabled(true);
       user.setEmail("pankajkumar.65778@gmail.com");
      User user1 = userRepository.save(user);
       assertThat(user1.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdateUserRoles(){
        User userRavi = userRepository.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesPerson = new Role(2);

        userRavi.getRoles().remove(roleEditor);
        userRavi.addRole(roleSalesPerson);
        userRepository.save(userRavi);
    }
    @Test
    public void testDeleteUserById(){
       Integer id = 2;
       userRepository.deleteById(id);
    }
}
