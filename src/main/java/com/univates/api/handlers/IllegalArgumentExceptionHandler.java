/**
 * Filename:    IllegalArgumentExceptionHandler.java
 *
 * Description: Implementation of the IllegalArgumentExceptionHandler class.
 *
 * Revision:    24 de abr. de 2024
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

import com.univates.api.records.response.ErrorResponseRecord;

/**
 * @author ev
 */
@ControllerAdvice
public class IllegalArgumentExceptionHandler
{
    @ExceptionHandler( IllegalArgumentException.class )
    @ResponseBody
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ErrorResponseRecord handler( IllegalArgumentException e )
    {
        return new ErrorResponseRecord( e.getMessage() );
    }
}
