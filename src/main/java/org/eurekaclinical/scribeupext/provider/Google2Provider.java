package org.eurekaclinical.scribeupext.provider;

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
import com.fasterxml.jackson.databind.JsonNode;
import org.eurekaclinical.scribeupext.profile.EurekaAttributesDefinition;
import org.eurekaclinical.scribeupext.profile.GoogleProfile;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.google2.Google2AttributesDefinition;

/**
 * Extracts the user's Google profile. This subclass of the original Google
 * OAuth2 provider fixes a bug where the scope is set incorrectly, and it 
 * populates the profile attributes that Eureka! Clinical expects.
 *
 * @author Andrew Post
 */
public class Google2Provider extends org.scribe.up.provider.impl.Google2Provider {

    public Google2Provider() {
        setScope(this.scope);
    }

    /**
     * Returns the user's profile using the attributes that Eureka! Clinical 
     * expects.
     * 
     * @param body the JSON response from the user profile request.
     * 
     * @return the user's profile.
     */
    @Override
    protected UserProfile extractUserProfile(final String body) {
        final GoogleProfile profile = new GoogleProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            profile.addAttribute(EurekaAttributesDefinition.USERNAME, JsonHelper.get(json, Google2AttributesDefinition.EMAIL));
            profile.addAttribute(EurekaAttributesDefinition.FIRSTNAME, JsonHelper.get(json, Google2AttributesDefinition.GIVEN_NAME));
            profile.addAttribute(EurekaAttributesDefinition.LASTNAME, JsonHelper.get(json, Google2AttributesDefinition.FAMILY_NAME));
            profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, Google2AttributesDefinition.NAME));
            profile.addAttribute(EurekaAttributesDefinition.EMAIL, JsonHelper.get(json, Google2AttributesDefinition.EMAIL));
        }
        return profile;
    }
}
