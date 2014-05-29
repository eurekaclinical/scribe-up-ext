package org.eurekaclinical.scribeupext.profile;

/*
 * #%L
 * Eureka! Clinical ScribeUP Extensions
 * %%
 * Copyright (C) 2014 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.scribe.up.profile.OAuthAttributesDefinition;
import org.scribe.up.profile.converter.Converters;

/**
 *
 * @author Andrew Post
 */
public class EurekaAttributesDefinition extends OAuthAttributesDefinition {
	public static final String USERNAME = "username";
	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";
	public static final String FULLNAME = "fullName";
	public static final String EMAIL = "email";
	public static final String ORGANIZATION = "organization";
	
	public EurekaAttributesDefinition() {
		String[] names = new String[]{
			USERNAME, FULLNAME, EMAIL, ORGANIZATION

		};
		for (String name : names) {
			addAttribute(name, Converters.stringConverter);
		}
	}
}
