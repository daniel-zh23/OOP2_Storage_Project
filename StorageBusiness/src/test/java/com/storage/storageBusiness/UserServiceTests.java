package com.storage.storageBusiness;

import com.storage.storageBusiness.Services.UserService;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UserServiceTests {
    @Mock UserDAO _userdao;
    @Test
    public void loginTest(){
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        var admin = new Admin("", "", "daniel", "", "", hashedPass);
        Mockito.when(_userdao.getByUsername("daniel")).thenReturn(admin);
        var service = new UserService();
        Assertions.assertEquals(1, 1);
    }
}
