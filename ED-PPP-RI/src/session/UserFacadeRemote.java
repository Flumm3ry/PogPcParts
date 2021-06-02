/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jerem
 */
@Remote
public interface UserFacadeRemote {

    UserDTO getUserById(int userId);

    UserDTO getUserByEmail(String email);

    boolean updateUser(UserDTO userDto);

    boolean updatePassword(int userId, String oldPassword, String newPassword);

    List<UserDTO> searchUsers(String searchTerm);

    boolean adminUpdateUser(UserDTO userDto);

    boolean addUser(UserDTO userDto);
    
}
