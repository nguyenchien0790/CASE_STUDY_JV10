package cotroller.user;

import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.account.Role;
import model.account.RoleName;
import model.account.User;
import service.role.RoleServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
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

        userService.serCurrentUser(user);
        return new ResponseMessenger("success");
    }

    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    public void  logout(){
        userService.serCurrentUser(null);
    }

    public List<User> displayDataUser(){
        return userService.findAll();

    }
}
