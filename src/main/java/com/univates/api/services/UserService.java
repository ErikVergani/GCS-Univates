/**
 * Filename:    UserService.java
 *
 * Description: Implementation of the UserService class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.univates.api.exception.NotFoundException;
import com.univates.api.exception.UsedLoginException;
import com.univates.api.models.User;
import com.univates.api.records.request.UserRecord;
import com.univates.api.records.response.UserResponseRecord;
import com.univates.api.repositories.UserRepository;

/**
 * @author ev
 */
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public List<User> getAll()
    {
        return userRepository.findAll();
    }
    
    public User getUserByLogin( String login )
    {
        return userRepository.findByLogin( login );
    }
    
    public User getUser( Integer id )
    {
        return userRepository.findById( id ).orElseThrow( () -> new NotFoundException( "Usuário não encontrado" ) );
    }
    
    public ResponseEntity<UserResponseRecord> saveUser( UserRecord userRecord )
    {
        boolean usedLogin = userRepository.existsByLogin( userRecord.login() );
        
        if ( usedLogin )
        {
            throw new UsedLoginException();
        }
        
        User user = new User();
        BeanUtils.copyProperties( userRecord, user );
        user.setPassword( encoder.encode( user.getPassword() ) );
        userRepository.save( user );
        
        return ResponseEntity.status( HttpStatus.CREATED ).body( new UserResponseRecord( user ) );
    }
    
    public ResponseEntity<UserResponseRecord> saveUser( Integer id, UserRecord userRecord )
    {
        Optional <User> oldUser = userRepository.findById( id );
        
        if ( oldUser.isEmpty() )
        {
            throw new NotFoundException( "Usuário não encontrado" );
        }
        
        User user = oldUser.get();
        BeanUtils.copyProperties( userRecord, user );
        user.setPassword( encoder.encode( user.getPassword() ) );
        userRepository.save( user );
        
        return ResponseEntity.status( HttpStatus.OK ).body( new UserResponseRecord( user ) );
    }
    
    public boolean authenticateUser( String login, String pass )
    {
        User user = userRepository.findByLogin( login );
        
        if ( user == null )
        {
            throw new NotFoundException( "Usuário Não encontrado" );
        }
        
        return user.getLogin().equals( login ) && encoder.matches( pass, user.getPassword() );
    }

    public ResponseEntity<List<UserResponseRecord>> getAllUsers()
    {
        List<UserResponseRecord> usersResponse = new ArrayList<>();

        for (User user : userRepository.findAll())
        {
            usersResponse.add( new UserResponseRecord( user ) );
        }

        return ResponseEntity.status( HttpStatus.OK ).body( usersResponse );
    }
}
