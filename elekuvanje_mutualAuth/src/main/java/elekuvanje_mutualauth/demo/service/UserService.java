package elekuvanje_mutualauth.demo.service;


import elekuvanje_mutualauth.demo.model.Role;
import elekuvanje_mutualauth.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String firstName, String lastName, String EMBG, Role role);
}
