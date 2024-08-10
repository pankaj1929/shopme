package com.shompme.admin;

import com.shompme.admin.user.RoleRepository;
import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateFirstRole(){
        Role roleAdmin = new Role("Admin","manage everything");
      Role saveRole =  roleRepository.save(roleAdmin);
      assertThat(saveRole.getId()).isGreaterThan(0);

    }
    @Test
    public void testCreateRestRoles(){
        Role roleSalesPerson = new Role("Salesperson","manage product price, "+"customer, shipping, orders sales and report");
        Role roleEditor = new Role("Editor","manage categories brand, "+"product, articles and menus");
        Role roleShipper = new Role("Shipper","view product, view orders, "+"and update order status");
        Role roleAssistant = new Role("Assistant","manage question and reviews");
         roleRepository.saveAll(List.of(roleSalesPerson,roleEditor,roleShipper,roleAssistant));

    }
}
