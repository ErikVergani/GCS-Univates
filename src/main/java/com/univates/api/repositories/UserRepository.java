/**
 * Filename:    UserRepository.java
 *
 * Description: Implementation of the UserRepository class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univates.api.models.User;


/**
 * @author ev
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    public boolean existsByLogin( String login );
    public User findByLogin( String login );
}
