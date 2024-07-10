/**
 * Filename:    NotFoundException.java
 *
 * Description: Implementation of the NotFoundException class.
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
public class NotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotFoundException( String message )
    {
        super( message );
    }
}
