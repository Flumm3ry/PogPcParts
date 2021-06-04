/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PppUsers;
import entity.UserDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author jerem
 */
@Stateless
public class UserFacade implements UserFacadeRemote {
    
    @PersistenceContext
    private EntityManager em;

    public UserFacade() {
    }

    private void create(PppUsers entity) {
        em.persist(entity);
    }

    private void edit(PppUsers entity) {
        em.merge(entity);
    }
    
    private PppUsers find(int id) {
        return em.find(PppUsers.class, id);
    }
    
    private PppUsers findByEmail(String email) {
        return em.createNamedQuery("Users.findByEmail", PppUsers.class)
            .setParameter("email", email)
            .getResultList().get(0);
    }
    
    private PppUsers DTO2DAO(UserDTO userDto) {
        if (userDto == null) return null;
        
        PppUsers user = new PppUsers();
        user.setUserid(userDto.getUserId());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAppgroup(userDto.getAppGroup());
        user.setActive(userDto.isActive());
        
        return user;
    }
    
    private UserDTO DAO2DTO(PppUsers user) {
        if (user == null) return null;
        
        return new UserDTO(user.getUserid(), user.getName(), user.getPhone(), user.getAddress(), user.getEmail(), user.getPassword(), user.getAppgroup(), user.getActive());
    }
    
    private String encryptPassword(String password){
        if (password == null) return null;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO getUserById(int userId) {
        return DAO2DTO(find(userId));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return DAO2DTO(findByEmail(email));
    }

    @Override
    public boolean updateUser(UserDTO userDto) {
        if (userDto == null || find(userDto.getUserId()) == null) return false;
        
        PppUsers user = DTO2DAO(userDto);
        user.setPassword(null);
        user.setAppgroup(null);
        user.setActive(null);
        
        edit(user);
        
        return true;
    }

    @Override
    public boolean updatePassword(int userId, String oldPassword, String newPassword) {
        // find the employee
        PppUsers user = find(userId);

        // check again - just to play it safe
        if (user == null) {
            return false;
        }
        
        if (!user.getPassword().equals(encryptPassword(oldPassword))) return false;
        // only need to update the password field
        user.setPassword(encryptPassword(newPassword));
        return true;
        
    }

    @Override
    public List<UserDTO> searchUsers(String searchTerm) {
        if (searchTerm == null) searchTerm = "";
        searchTerm = "%" + searchTerm + "%";
        
        return em.createQuery(
            "SELECT u FROM Users u WHERE u.name LIKE :searchTerm OR u.email LIKE :searchTerm", PppUsers.class)
            .setParameter("searchTerm", searchTerm)
            .getResultList()
            .stream()
            .map(u -> DAO2DTO(u))
            .collect(Collectors.toList());
    }

    @Override
    public boolean adminUpdateUser(UserDTO userDto) {
        if (userDto == null || find(userDto.getUserId()) == null) return false;
        
        userDto.setPassword(encryptPassword(userDto.getPassword()));
        
        edit(DTO2DAO(userDto));
        
        return true;
    }

    @Override
    public boolean addUser(UserDTO userDto) {
        if (userDto == null || find(userDto.getUserId()) != null) return false;
        
        userDto.setActive(true);
        userDto.setAppGroup("USER");
        userDto.setPassword(encryptPassword(userDto.getPassword()));
        
        create(DTO2DAO(userDto));
        
        return true;
    }
}
