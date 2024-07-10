/**
 * Filename:    UserAlreadyRegisteredException.java
 *
 * Description: Implementation of the UserAlreadyRegisteredException class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.exception;


/**
 * @author ev
 */
public class UserRegisterException extends RuntimeException
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * UserAlreadyRegisteredException
     *
     */
    public UserRegisterException( String message )
    {
        super( message );
    }
}
