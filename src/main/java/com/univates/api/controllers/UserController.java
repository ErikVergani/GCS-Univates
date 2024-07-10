/**
 * Filename:    UserController.java
 *
 * Description: Implementation of the UserController class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.univates.api.records.request.UserRecord;
import com.univates.api.records.response.UserResponseRecord;
import com.univates.api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;


/**
 * @author ev
 */
@RestController
@RequestMapping( "/api/users" )
public class UserController
{
    @Autowired
    UserService userService;
    
    @PostMapping
    @Operation( description = "Realiza o cadastro de um usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as informações do novo usuário" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Login indisponível" ),
                           } )
    public ResponseEntity<UserResponseRecord> addUser( @RequestHeader String login, @RequestHeader String pass, @RequestBody @Valid UserRecord ur, HttpServletRequest request ) throws Exception
    {
        return userService.saveUser( ur );
    }
    
    @PutMapping( "/{id}" )
    @Operation( description = "Atualiza as informações de um usuário ( Disponível apenas para ADM )" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as informações atualizadas de um determinado usuário" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Usuário não encontrado" ),
                           } )
    public ResponseEntity<UserResponseRecord> updateUser( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @PathVariable( value = "id" ) Integer id, @RequestBody @Valid UserRecord ur, HttpServletRequest request )
    {
        return userService.saveUser( id, ur );
    }

    @GetMapping
    public ResponseEntity<List<UserResponseRecord>> getUsers()
    {
        return userService.getAllUsers();
    }
}
