package cotroller.user;
import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.account.Role;
import model.account.RoleName;
import model.account.User;
import service.role.IRoleService;
import service.role.RoleServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();
    IRoleService roleService = new RoleServiceIMPL();
    public int getLastId(){
        return userService.getLastId();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO){
        if (userService.existsByUsername(signUpDTO.getUsername())){
            return new ResponseMessenger("username_exists");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())){
            return new ResponseMessenger("email_exists");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(new RoleServiceIMPL().findByRoleName(RoleName.USER));
        User user = new User(signUpDTO.getId(),signUpDTO.getName(),signUpDTO.getUsername(),signUpDTO.getPassword(),signUpDTO.getEmail(),roles);
        userService.save(user);
        return  new ResponseMessenger("Success");
    }

    public ResponseMessenger login(SignInDTO signInDTO) {
        User user = userService.findByName(signInDTO.getUsername());
        if (user == null){
            return new ResponseMessenger("username_not_found");
        }
        if (!user.getPassword().equals(signInDTO.getPassword())){
            return new ResponseMessenger("password_wrong");
        }
        if(userService.findByName(signInDTO.getUsername()).isStatus()){
            return new ResponseMessenger("blocked");
        }

        userService.saveCurrentUser(user);
        return new ResponseMessenger("success");
    }

    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    public void  logout(){
        userService.saveCurrentUser(null);
    }

    public List<User> displayDataUser(){
        List<User> userList = new ArrayList<>(userService.findAll());
        userList.remove(0);
        return userList;
    }
    public List<User> showListUser(){
        List<User> userList = new ArrayList<>(userService.findAll());
        return userList;
    }

    public ResponseMessenger deleteUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        userService.remote(id);
        return new ResponseMessenger("success");
    }

    public ResponseMessenger changeRole(int id, String roleName) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        if (!roleName.equals("USER") && !roleName.equals("PM")) {
            return new ResponseMessenger("invalid_role");
        }
        Role role = roleName.equals("USER") ? roleService.findByRoleName(RoleName.USER) : roleService.findByRoleName(RoleName.PM);
        userService.changeRole(id, role);
        return new ResponseMessenger("success");

    }

    public ResponseMessenger blockUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        userService.changeStatus(id);
        boolean check = userService.findById(id).isStatus();
        if (check) {
            return new ResponseMessenger("blocked");
        } else {
            return new ResponseMessenger("unblocked");
        }
    }
    public ResponseMessenger changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(currentUser.getPassword())) {
            return new ResponseMessenger("not_match");
        }
        currentUser.setPassword(newPassword);
        userService.updateData();
        return new ResponseMessenger("success");
    }
    public User detailUser(String username){
        return userService.findByName(username);
    }
    public void editUser(String username, User newUser){
        User user = userService.findByName(username);
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
    }


    public void editUser(User user,int id) {
        User user1 = userService.findById(id);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        userService.save(user1);
    }
}
