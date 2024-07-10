/**
 * Filename:    UsedLoginException.java
 *
 * Description: Implementation of the UsedLoginException class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.univates.api.exception.UsedLoginException;
import com.univates.api.records.response.ErrorResponseRecord;

/**
 * @author ev
 */
@ControllerAdvice
public class UsedLoginExceptionHandler
{
    @ExceptionHandler( UsedLoginException.class )
    @ResponseBody
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ErrorResponseRecord handler( UsedLoginException ex )
    {
       return new ErrorResponseRecord( ex.getMessage() );
    }
}
