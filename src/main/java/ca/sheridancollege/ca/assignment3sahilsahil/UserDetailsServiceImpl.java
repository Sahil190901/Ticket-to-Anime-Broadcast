package ca.sheridancollege.ca.assignment3sahilsahil;

import ca.sheridancollege.ca.assignment3sahilsahil.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



    @AllArgsConstructor
    @Service


    public class UserDetailsServiceImpl  implements UserDetailsService {


        @Autowired
        private UserRepository secRep;


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            ca.sheridancollege.ca.assignment3sahilsahil.beans.User user=secRep.findUserAccount(username);
            if(user== null){
                System.out.println("user was not found");
                throw new UsernameNotFoundException("User not found");
            }
            //Get the list of roles based on the user id
            List<String> roles =secRep.getRolesById(user.getUserid());
            System.out.println(roles);
            System.out.println(user);

            //Convert our list of strings into a list of granted authorities
            List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
            for (String role: roles){
                grantList.add(new SimpleGrantedAuthority(role));

            }
            //create a spring user based on the above information
            //import this user from spring core
            User SpringUser= new User(user.getUserName(),
                    user.getEncryptedPassword(), grantList);

            return  (UserDetails) SpringUser;
        }
    }
