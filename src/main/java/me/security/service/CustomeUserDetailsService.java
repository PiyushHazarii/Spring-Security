package me.security.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.security.model.entity.Users;
import me.security.model.entity.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("User Not exists");
        }
    }




}
