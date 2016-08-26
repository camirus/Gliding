/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.dao.UserDao;
import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.gliding.model.User;
import com.multimedianetwork.gliding.utils.PasswordGenerator;
import com.multimedianetwork.gliding.utils.PasswordService;

/**
 *
 * @author Camelia Rus
 */
public class UserManager extends AbstractManager<User> {

    public UserManager() {
        super(User.class);
        dao = new UserDao();
    }

    public User getByUsernameAndPassword(String username, String password) {
        return new UserDao().getByUsernameAndPassword(username, password);
    }

    public User createUserForMember(Member member) throws Exception {
        if ((member.getEmail() != null) && (!member.getEmail().isEmpty())) {
            User user = new User();
            user.setUsername(member.getEmail());
            user.setEmail(member.getEmail());
            user.setRole("MEMBRU");
            
            final String password = PasswordGenerator.getInstance().generatePassword();
            String encryptedPassword = PasswordService.getInstance().encrypt(password);

            user.setOriginalPassword(password);
            user.setPassword(encryptedPassword);
            return user;

        } else {
            throw new Exception("Email missing");
        }
    }
}
