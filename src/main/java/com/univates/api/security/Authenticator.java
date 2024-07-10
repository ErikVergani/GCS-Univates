/**
 * Filename:    Auth.java
 *
 * Description: Implementation of the Auth class.
 *
 * Revision:    19 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ev
 */
@Service
public class Authenticator
{
    @Value( "${api.user}" )
    private String apiUser;
    
    @Value( "${api.password}" )
    private String apiPass;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public boolean authenticate( String user, String password )
    {
        return true;
    }
}
