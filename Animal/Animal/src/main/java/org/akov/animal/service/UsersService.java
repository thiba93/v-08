package org.akov.animal.service;

import org.akov.animal.model.Users;
import org.akov.animal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public Users byId(Integer id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmailLikeIgnoreCase(username);
        if (users == null){
            throw new UsernameNotFoundException("User not found");
        }
        return users;
    }
}
