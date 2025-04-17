package com.oladapo.EasySchool.security;

import com.oladapo.EasySchool.model.Person;
import com.oladapo.EasySchool.model.Roles;
import com.oladapo.EasySchool.repository.PersonRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UsernameAndAuthenticationProvider implements AuthenticationProvider {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public UsernameAndAuthenticationProvider(PersonRepository personRepository,
                                             PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepository.readByEmail(email);
        if (null != person && person.getPersonId() > 0 &&
                passwordEncoder.matches(password, person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(
                    email, null, getGrantedAuthorities(person.getRoles())
            );
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

// GrantedAuthority is an interface in spring security framework that deals with roles
// simpleGrantedAuthority is a class that extend GrantedAuthority and hold the role information
