/**
 * Filename:    EmailController.java
 *
 * Description: Implementation of the EmailController class.
 *
 * Revision:    24 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univates.api.records.response.EmailResponseRecord;
import com.univates.api.services.EmailService;



/**
 * @author ev
 */
@Controller
@RequestMapping( "/api/email" )
public class EmailController
{
    @Autowired
    private EmailService emailService;
    
    @PostMapping( "/{id}" )
    public ResponseEntity <EmailResponseRecord> sendEmail( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @PathVariable( value = "id" ) Integer userId )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( new EmailResponseRecord( emailService.sendAlertEmail( userId ) ) );
    }
}
