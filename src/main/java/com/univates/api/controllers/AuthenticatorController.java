/**
 * Filename:    AuthenticatorController.java
 *
 * Description: Implementation of the AuthenticatorController class.
 *
 * Revision:    23 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/api/auth" )
public class AuthenticatorController
{
    @Autowired
    private UserService userService;
    
    @PostMapping
    @Operation( description = "Realiza a autenticação do usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna true/false para o resultado da autenticação" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                           } )
    public ResponseEntity <Boolean> authenticateUser( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String userLogin, @RequestHeader String userPass )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( userService.authenticateUser( userLogin, userPass ) );
    }
}
