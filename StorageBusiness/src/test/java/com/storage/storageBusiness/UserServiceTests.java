package com.storage.storageBusiness;

import com.storage.storageBusiness.Services.UserService;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Admin;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests extends BaseTests{
    @Mock UserDAO _userDao;

    @InjectMocks
    UserService _userService;


    @Test
    public void login_should_return_correct_data() throws Exception{
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Mockito.when(_userDao.getByUsername("daniel")).thenReturn((Admin)adm);

        var result = _userService.login("daniel", "admin");

        Assertions.assertEquals(1, result.getUserId());
        Assertions.assertEquals("Admin", result.getType());
        Assertions.assertEquals(true, result.isFirstLogin());
    }

    @Test
    public void login_should_return_null_when_wrong_password() throws Exception{
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Mockito.when(_userDao.getByUsername("daniel")).thenReturn((Admin)adm);

        var result = _userService.login("daniel", "admin2");

        Assertions.assertEquals(null, result);
    }

    @Test
    public void login_should_return_null_when_not_found() {
        Mockito.when(_userDao.getByUsername("daniel")).thenReturn(null);
        var result = _userService.login("daniel", "admin");

        Assertions.assertEquals(null, result);
    }

    @Test
    public void checkUsername_should_return_true_when_found() {
        Mockito.when(_userDao.getUsernames()).thenReturn(Arrays.asList("daniel", "kris", "ivan").stream());
        var result = _userService.checkUsername("daniel");

        Assertions.assertEquals(true, result);
    }

    @Test
    public void checkUsername_should_return_false_when_not_found() {
        Mockito.when(_userDao.getUsernames()).thenReturn(Arrays.asList("daniel", "kris", "ivan").stream());
        var result = _userService.checkUsername("test");

        Assertions.assertEquals(false, result);
    }

    @Test
    public void changePassword_should_return_true_on_completion() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        var admin = (Admin) adm;

        Mockito.when(_userDao.getByUsername("daniel")).thenReturn(admin);
        var result = _userService.changePassword("daniel", "admin2");

        Assertions.assertEquals(true, result);
    }

    @Test
    public void changePassword_should_change_password() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        var expectedNewPass = "1c142b2d01aa34e9a36bde480645a57fd69e14155dacfab5a3f9257b77fdc8d8";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        var admin = (Admin) adm;

        Mockito.when(_userDao.getByUsername("daniel")).thenReturn(admin);
        var result = _userService.changePassword("daniel", "admin2");

        Assertions.assertEquals(expectedNewPass, admin.getPassword());
    }

    @Test
    public void changePassword_should_change_isFirstLogin_flag() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        var admin = (Admin) adm;

        Mockito.when(_userDao.getByUsername("daniel")).thenReturn(admin);
        var result = _userService.changePassword("daniel", "admin2");

        Assertions.assertEquals(false, admin.isFirstLogin());
    }

    @Test
    public void checkId_should_return_true_when_found() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Class<?> clas2 = Admin.class;
        Object adm2 = clas2.getDeclaredConstructor().newInstance();
        Field f12= adm2.getClass().getSuperclass().getDeclaredField("id");
        Field f22= adm2.getClass().getSuperclass().getDeclaredField("password");
        Field f32= adm2.getClass().getSuperclass().getDeclaredField("username");
        f12.setAccessible(true);
        f12.set(adm2,2);
        f22.setAccessible(true);
        f22.set(adm2, hashedPass);
        f32.setAccessible(true);
        f32.set(adm2, "mihalev");
        var admin = (User) adm;
        var admin2 = (User) adm2;

        Mockito.when(_userDao.getAll()).thenReturn(Arrays.asList(admin, admin2).stream());
        var result = _userService.checkId(1);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void checkId_should_return_false_when_not_found() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Class<?> clas2 = Admin.class;
        Object adm2 = clas2.getDeclaredConstructor().newInstance();
        Field f12= adm2.getClass().getSuperclass().getDeclaredField("id");
        Field f22= adm2.getClass().getSuperclass().getDeclaredField("password");
        Field f32= adm2.getClass().getSuperclass().getDeclaredField("username");
        f12.setAccessible(true);
        f12.set(adm2,2);
        f22.setAccessible(true);
        f22.set(adm2, hashedPass);
        f32.setAccessible(true);
        f32.set(adm2, "mihalev");
        var admin = (User) adm;
        var admin2 = (User) adm2;

        Mockito.when(_userDao.getAll()).thenReturn(Arrays.asList(admin, admin2).stream());
        var result = _userService.checkId(3);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void deleteById_should_change_isActive_flag_to_false() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Class<?> clas2 = Admin.class;
        Object adm2 = clas2.getDeclaredConstructor().newInstance();
        Field f12= adm2.getClass().getSuperclass().getDeclaredField("id");
        Field f22= adm2.getClass().getSuperclass().getDeclaredField("password");
        Field f32= adm2.getClass().getSuperclass().getDeclaredField("username");
        f12.setAccessible(true);
        f12.set(adm2,2);
        f22.setAccessible(true);
        f22.set(adm2, hashedPass);
        f32.setAccessible(true);
        f32.set(adm2, "mihalev");
        var admin = (User) adm;
        var admin2 = (User) adm2;

        Mockito.when(_userDao.getAll()).thenReturn(Arrays.asList(admin, admin2).stream());
        var result = _userService.deleteById(1);

        Assertions.assertEquals(false, admin.isActive());
    }

    @Test
    public void deleteById_should_return_true_on_completion() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Class<?> clas2 = Admin.class;
        Object adm2 = clas2.getDeclaredConstructor().newInstance();
        Field f12= adm2.getClass().getSuperclass().getDeclaredField("id");
        Field f22= adm2.getClass().getSuperclass().getDeclaredField("password");
        Field f32= adm2.getClass().getSuperclass().getDeclaredField("username");
        f12.setAccessible(true);
        f12.set(adm2,2);
        f22.setAccessible(true);
        f22.set(adm2, hashedPass);
        f32.setAccessible(true);
        f32.set(adm2, "mihalev");
        var admin = (User) adm;
        var admin2 = (User) adm2;

        Mockito.when(_userDao.getAll()).thenReturn(Arrays.asList(admin, admin2).stream());
        var result = _userService.deleteById(1);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void deleteById_should_return_false_on_invalid_id() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Admin.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        Class<?> clas2 = Admin.class;
        Object adm2 = clas2.getDeclaredConstructor().newInstance();
        Field f12= adm2.getClass().getSuperclass().getDeclaredField("id");
        Field f22= adm2.getClass().getSuperclass().getDeclaredField("password");
        Field f32= adm2.getClass().getSuperclass().getDeclaredField("username");
        f12.setAccessible(true);
        f12.set(adm2,2);
        f22.setAccessible(true);
        f22.set(adm2, hashedPass);
        f32.setAccessible(true);
        f32.set(adm2, "mihalev");
        var admin = (User) adm;
        var admin2 = (User) adm2;

        Mockito.when(_userDao.getAll()).thenReturn(Arrays.asList(admin, admin2).stream());
        var result = _userService.deleteById(4);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void adjustRatingBy_should_increase_rating_correctly() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Agent.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");

        Mockito.when(_userDao.get(1)).thenReturn((User)adm);
        _userService.adjustRatingBy(1, 2.0);

        Assertions.assertEquals(2.0, ((Agent)adm).getRating());
    }

    @Test
    public void adjustRatingBy_should_decrease_rating_correctly() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Agent.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        Field f4= adm.getClass().getDeclaredField("rating");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        f4.setAccessible(true);
        f4.set(adm, 4.0);

        Mockito.when(_userDao.get(1)).thenReturn((User)adm);
        _userService.adjustRatingBy(1, -2.0);

        Assertions.assertEquals(2.0, ((Agent)adm).getRating());
    }

    @Test
    public void adjustRatingBy_should_set_rating_to_0_when_lower_than_0() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Agent.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        Field f4= adm.getClass().getDeclaredField("rating");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        f4.setAccessible(true);
        f4.set(adm, 0.0);

        Mockito.when(_userDao.get(1)).thenReturn((User)adm);
        _userService.adjustRatingBy(1, -2.0);

        Assertions.assertEquals(0.0, ((Agent)adm).getRating());
    }

    @Test
    public void adjustRatingBy_should_set_rating_to_10_when_bigger_than_10() throws Exception {
        var hashedPass = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        Class<?> clas = Agent.class;
        Object adm = clas.getDeclaredConstructor().newInstance();
        Field f1= adm.getClass().getSuperclass().getDeclaredField("id");
        Field f2= adm.getClass().getSuperclass().getDeclaredField("password");
        Field f3= adm.getClass().getSuperclass().getDeclaredField("username");
        Field f4= adm.getClass().getDeclaredField("rating");
        f1.setAccessible(true);
        f1.set(adm,1);
        f2.setAccessible(true);
        f2.set(adm, hashedPass);
        f3.setAccessible(true);
        f3.set(adm, "daniel");
        f4.setAccessible(true);
        f4.set(adm, 9.0);

        Mockito.when(_userDao.get(1)).thenReturn((User)adm);
        _userService.adjustRatingBy(1, 2.0);

        Assertions.assertEquals(10.0, ((Agent)adm).getRating());
    }
}
