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
 * A base class for creating profile objects. It implements getters for the
 * attributes that Eureka! Clinical expects.
 * 
 * Concrete implementations must specify an unique profile type string. 
 * 
 * Provider objects set attributes by calling {@link #addAttribute(java.lang.String, java.lang.Object) }
 * with one of the constants in {@link EurekaAttributesDefinition} as the 
 * attribute name.
 * 
 * @author Andrew Post
 * @see EurekaAttributesDefinition
 */
public abstract class EurekaProfile extends BaseOAuthProfile implements CommonProfile {

    public abstract String getType();

    /**
     * Returns the supported attributes.
     * 
     * @return the supported attributes.
     */
    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return OAuthAttributesDefinitions.eurekaDefinition;
    }

    /**
     * Returns the user's email address.
     * 
     * @return the user's email address.
     */
    @Override
    public String getEmail() {
        return (String) get(EurekaAttributesDefinition.EMAIL);
    }

    /**
     * Returns the user's first name.
     * 
     * @return the user's first name.
     */
    @Override
    public String getFirstName() {
        return (String) get(EurekaAttributesDefinition.FIRSTNAME);
    }

    /**
     * Returns the user's last name.
     * 
     * @return the user's last name.
     */
    @Override
    public String getFamilyName() {
        return (String) get(EurekaAttributesDefinition.LASTNAME);
    }

    /**
     * Returns the user's full name.
     * 
     * @return the user's full name.
     */
    @Override
    public String getDisplayName() {
        return (String) get(EurekaAttributesDefinition.FULLNAME);
    }

    /**
     * Returns the user's username.
     * 
     * @return the user's username. Cannot be <code>null</code>.
     */
    @Override
    public String getUsername() {
        return (String) get(EurekaAttributesDefinition.USERNAME);
    }

    /**
     * Returns the user's gender.
     * 
     * @return the user's geneder.
     */
    @Override
    public Gender getGender() {
        return null;
    }

    /**
     * Returns a user-specific locale.
     * 
     * @return a user-specific locale.
     */
    @Override
    public Locale getLocale() {
        return null;
    }

    /**
     * Return a URL to the user's profile picture.
     * 
     * @return a URL to the user's profile picture.
     */
    @Override
    public String getPictureUrl() {
        return null;
    }

    /**
     * Returns a URL to the user's profile.
     * 
     * @return a URL to the user's profile.
     */
    @Override
    public String getProfileUrl() {
        return null;
    }

    /**
     * Returns the user's location (e.g., city, state).
     * 
     * @return the user's location.
     */
    @Override
    public String getLocation() {
        return null;
    }

}
