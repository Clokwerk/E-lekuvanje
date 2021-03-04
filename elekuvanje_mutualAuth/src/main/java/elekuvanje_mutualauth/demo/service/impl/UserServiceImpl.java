package elekuvanje_mutualauth.demo.service.impl;


import elekuvanje_mutualauth.demo.model.Role;
import elekuvanje_mutualauth.demo.model.User;
import elekuvanje_mutualauth.demo.repository.UserRepository;
import elekuvanje_mutualauth.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public User register(String username, String password, String repeatPassword, String firstName, String lastName, String EMBG, Role role)  {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            return null;
        }
        if(!password.equals(repeatPassword)){
            return null;
        }
        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            return null;
        User user=new User(EMBG,username,password,firstName,lastName,role);
        return this.userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
}
