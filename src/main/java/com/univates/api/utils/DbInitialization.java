/**
 * Filename:    DbInitialization.java
 *
 * Description: Implementation of the DbInitialization class.
 *
 * Revision:    23 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.univates.api.records.request.UserRecord;
import com.univates.api.services.UserService;

/**
 * @author ev
 */
@Component
public class DbInitialization implements CommandLineRunner
{

    @Autowired
    private UserService userService;
    
    @Override
    public void run( String... args ) throws Exception
    {
        if ( userService.getUserByLogin( "anonymous" ) == null )
        {
            userService.saveUser( new UserRecord( "anonymous" ,"anonymous" ) );
            System.out.println( "Created root user!" );
        }
    }
    
}
