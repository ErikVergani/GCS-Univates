/**
 * Filename:    EventRecord.java
 *
 * Description: Implementation of the EventRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.request;

import java.time.LocalDateTime;

/**
 * @author ev
 */
public record EventRecord( String name, LocalDateTime date ){}
