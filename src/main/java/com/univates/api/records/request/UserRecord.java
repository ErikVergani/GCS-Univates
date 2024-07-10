/**
 * Filename:    UserRecord.java
 *
 * Description: Implementation of the UserRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author ev
 */
public record UserRecord( @NotNull @NotBlank String login, @NotNull @NotBlank String password, @NotNull @NotBlank String name, @NotNull @NotBlank String email )
{
    public UserRecord( String login, String name )
    {
        this( login, "a#$!4FWD3241ED!@#44f23F4g433465@#$6t&*57", name, null );
    }
}
