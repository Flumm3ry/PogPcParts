/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserDTO;
import javax.ejb.Stateless;

/**
 *
 * @author jerem
 */
@Stateless
public class UserFacade implements UserFacadeRemote {

    @Override
    public UserDTO getUserById(int userId) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public void updateUser(UserDTO userDto) {
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword) {
    }

    @Override
    public UserDTO[] searchUsers(String searchTerm) {
        return null;
    }

    @Override
    public void adminUpdateUser(String appGroup, boolean isActive) {
    }
}
