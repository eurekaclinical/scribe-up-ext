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
import org.eurekaclinical.scribeupext.profile.TwitterProfile;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.twitter.TwitterAttributesDefinition;

/**
 *
 * @author Andrew Post
 */
public class SSLTwitterProvider extends org.scribe.up.provider.impl.TwitterProvider {

    @Override
    protected String getProfileUrl() {
        return "https://api.twitter.com/1.1/account/verify_credentials.json";
    }

    @Override
    protected UserProfile extractUserProfile(final String body) {
        final TwitterProfile profile = new TwitterProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            profile.addAttribute(EurekaAttributesDefinition.USERNAME, JsonHelper.get(json, TwitterAttributesDefinition.SCREEN_NAME));
            profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, TwitterAttributesDefinition.NAME));
        }
        return profile;
    }

}
