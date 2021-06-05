/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.UserDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import session.UserFacadeRemote;

/**
 *
 * @author jerem
 */
@Named(value = "myUserManagedBean")
@RequestScoped
@ManagedBean
public class MyUserManagedBean implements Serializable{

    @EJB(mappedName="ejb/UserFacadeRemote")
    private UserFacadeRemote userFacade;

    private Integer userId;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String password;
    
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    
    private String searchTerm;
    private List<UserDTO> userList;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    private String appGroup;
    private boolean active;

    /**
     * Creates a new instance of MyUserManagedBean
     */
    public MyUserManagedBean() {
    }
    
    public String addUser() {
        UserDTO userDto = new UserDTO(null, name, phone, address, email, password, appGroup, active);
        
        return userFacade.addUser(userDto) ? "success" : "fail";
    }
    
    public String updateUser() {
        UserDTO userDto = new UserDTO(userId, name, phone, address, email, password, appGroup, active);
        
        return userFacade.updateUser(userDto) ? "success" : "fail";
    }
    
    public String adminUpdateUser() {
        UserDTO userDto = new UserDTO(userId, name, phone, address, email, password, appGroup, active);
        
        return userFacade.adminUpdateUser(userDto) ? "success" : "fail";
    }
    
    public String searchUsers() {
        try {
            setUserList(userFacade.searchUsers(searchTerm));
            return "success";
        } catch (Exception e) {
        }
        
        return "fail";
    }
    
    public String getUser(Integer userIdToGet) {
        UserDTO userDto = userFacade.getUserById(userIdToGet);
        
        if (userDto == null) return "fail";

        userId = userDto.getUserId();
        name = userDto.getName();
        phone = userDto.getPhone();
        address = userDto.getAddress();
        email = userDto.getEmail();
        
        return "success";
    }
    
    public String getCurrentUser() {
        String loggedInEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        
        UserDTO userDto = userFacade.getUserByEmail(loggedInEmail);
        
        if (userDto == null) return "fail";
        
        userId = userDto.getUserId();
        name = userDto.getName();
        phone = userDto.getPhone();
        address = userDto.getAddress();
        email = userDto.getEmail();
        
        return "success";
    }
    
    public String changePassword() {
        String loggedInEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        
        UserDTO userDto = userFacade.getUserByEmail(loggedInEmail);
        
        if (userDto == null) return "fail";
        
        return userFacade.updatePassword(userDto.getUserId(), oldPassword, newPassword) ? "success" : "fail";
    }
}
