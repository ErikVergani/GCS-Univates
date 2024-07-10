/**
 * Filename:    LogRepository.java
 *
 * Description: Implementation of the LogRepository class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univates.api.models.Log;

/**
 * @author ev
 */
public interface LogRepository extends JpaRepository <Log, Integer>
{
    
}
