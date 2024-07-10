/**
 * Filename:    RegistrationRepository.java
 *
 * Description: Implementation of the RegistrationRepository class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univates.api.models.Event;
import com.univates.api.models.Registration;
import com.univates.api.models.User;

/**
 * @author ev
 */
@Repository
public interface RegistrationRepository extends JpaRepository <Registration, Integer>
{

    List<Registration> findByUser( User user );
    Optional <Registration> findByEventAndUser( Event event, User user );
}
