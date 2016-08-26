/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.User;
import com.multimedianetwork.gliding.utils.PasswordService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class UserManagerTest {
    
    public UserManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getByUsernameAndPassword method, of class UserManager.
     */
//    @Test
    public void testInsert() {
        String username = "admin";
        String password = PasswordService.getInstance().encrypt("gliding123");
        String role = "ADMIN";
        UserManager instance = new UserManager();
        User user = new User(username, password, role, "camirus@gmail.com");
        instance.insert(user);
    }
}