/**
 * Filename:    UserResponseRecord.java
 *
 * Description: Implementation of the UserResponseRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.response;

import com.univates.api.models.User;

import java.util.List;

/**
 * @author ev
 */
public record UserResponseRecord( String name, String login, String email, String color )
{
    public UserResponseRecord( User user )
    {
        this( user.getName(), user.getLogin(), user.getEmail(), "AZUL" );
    }
}
