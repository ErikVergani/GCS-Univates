/**
 * Filename:    LogsService.java
 *
 * Description: Implementation of the LogsService class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univates.api.models.Log;
import com.univates.api.models.User;
import com.univates.api.repositories.LogRepository;

/**
 * @author ev
 */
@Service
public class LogsService
{
    @Autowired
    private LogRepository logRepository;
    
    public void saveLog( User user, String url, String method )
    {
        logRepository.save( new Log( user, url, method ) );
    }
}
