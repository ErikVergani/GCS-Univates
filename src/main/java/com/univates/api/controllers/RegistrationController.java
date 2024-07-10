/**
 * Filename:    RegistrationController.java
 *
 * Description: Implementation of the RegistrationController class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.records.request.RegistrationRecord;
import com.univates.api.records.response.RegistrationResponseRecord;
import com.univates.api.services.RegistrationService;
import com.univates.api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/api/registration" )
public class RegistrationController
{
    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    @Operation( description = "Realiza uma nova inscrição em um determinado evento" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as informações da inscrição" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                             @ApiResponse ( responseCode = "500", description = "Usuário já registrado no evento" )
                           } )
    public ResponseEntity <RegistrationResponseRecord> addRegistration( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @RequestBody @Valid RegistrationRecord rr, HttpServletRequest request )
    {
        return registrationService.addRegistration( rr );
    }
    
    @GetMapping( "/{userId}" )
    @Operation( description = "Retorna as incrições de um usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as inscrições de um determinado usuário" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Usuário não encontrado" ),
                           } )
    public ResponseEntity <List<RegistrationResponseRecord>> getUserRegistrations( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @PathVariable( value = "userId" ) Integer userId, HttpServletRequest request  )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( registrationService.getRegistrations( userService.getUser( userId ) ) );
    }
    
    @PostMapping( "/cancel" ) 
    @Operation( description = "Cancela a inscrição de um usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna a incrição com o status \"cancelada\"" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                             @ApiResponse ( responseCode = "500", description = "Usuário já cancelou a inscrição no evento" )
                           } )
    ResponseEntity <RegistrationResponseRecord> cancelRegistration( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @RequestParam( value = "eventId" ) Integer eventId, @RequestParam( value = "userId" ) Integer userId, HttpServletRequest request )
    {
        return registrationService.cancelRegistration( eventId, userId );
    }
    
    @PostMapping( "/checkin" ) 
    @Operation( description = "Realiza o check-in do usuário no evento" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna a incrição do evento com o status \"check-in realizado\"" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                             @ApiResponse ( responseCode = "500", description = "Check-in do usuário já foi realizado no evento" )
                           } )
    ResponseEntity<RegistrationResponseRecord> checkIn( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @RequestParam( value = "eventId" ) Integer eventId, @RequestParam( value = "userId" ) Integer userId, HttpServletRequest request )
    {
        return registrationService.checkIn( eventId, userId );
    }
}
