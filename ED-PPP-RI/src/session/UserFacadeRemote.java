/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserDTO;
import javax.ejb.Remote;

/**
 *
 * @author jerem
 */
@Remote
public interface UserFacadeRemote {

    UserDTO getUserById(int userId);

    UserDTO getUserByEmail(String email);

    void updateUser(UserDTO userDto);

    void updatePassword(String oldPassword, String newPassword);

    UserDTO[] searchUsers(String searchTerm);

    void adminUpdateUser(String appGroup, boolean isActive);
    
}
