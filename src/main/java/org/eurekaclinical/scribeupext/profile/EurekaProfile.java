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

import java.util.Locale;
import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;

/**
 *
 * @author arpost
 */
public class EurekaProfile extends BaseOAuthProfile implements CommonProfile {
	
	private static final long serialVersionUID = 1;

	@Override
    protected AttributesDefinition getAttributesDefinition() {
        return OAuthAttributesDefinitions.eurekaDefinition;
    }
	
	@Override
	public String getEmail() {
		return (String) get(EurekaAttributesDefinition.EMAIL);
	}

	@Override
	public String getFirstName() {
		return (String) get(EurekaAttributesDefinition.FIRSTNAME);
	}

	@Override
	public String getFamilyName() {
		return (String) get(EurekaAttributesDefinition.LASTNAME);
	}

	@Override
	public String getDisplayName() {
		return (String) get(EurekaAttributesDefinition.FULLNAME);
	}

	@Override
	public String getUsername() {
		return (String) get(EurekaAttributesDefinition.USERNAME);
	}

	@Override
	public Gender getGender() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Locale getLocale() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getPictureUrl() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getProfileUrl() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getLocation() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
