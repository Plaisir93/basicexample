/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.basicexample;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.User;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also BasicexampleDaoTest and liquibase.xml
//@Entity(name = "basicexample.Item")
//@Table(name = "basicexample_item")
public class PlrUser {
	
	private Integer id;
	
	private String familyName;
	
	private String firstName;
	
	// Setters
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setFamilyName(String faName) {
		this.familyName = faName;
	}
	
	public void setFirstName(String fiName) {
		this.firstName = fiName;
	}
	
	// getters
	
	public Integer getId() {
		return this.id;
	}
	
	public String getFamilyName() {
		return this.familyName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
}
