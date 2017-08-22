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
import org.eurekaclinical.scribeupext.GlobusApi;
import org.eurekaclinical.scribeupext.profile.EurekaAttributesDefinition;
import org.eurekaclinical.scribeupext.profile.GlobusAttributesDefinition;
import org.eurekaclinical.scribeupext.profile.GlobusProfile;
import org.eurekaclinical.scribeupext.service.GlobusOAuth20ServiceImpl;
import java.util.concurrent.TimeUnit;
import org.scribe.model.OAuthConfig;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.provider.BaseOAuth20Provider;
import org.scribe.up.provider.exception.HttpException;

/**
 *
 * @author Andrew Post
 */
public class GlobusProvider extends BaseOAuth20Provider {
	@Override
	protected GlobusProvider newProvider() {
		return new GlobusProvider();
	}
	
	@Override
	protected void internalInit() {
		this.service = new GlobusOAuth20ServiceImpl(new GlobusApi(),
			new OAuthConfig(this.key, this.secret, this.callbackUrl,
			SignatureType.Header, "user", null), this.proxyHost, 
				this.proxyPort);
	}
	
	@Override
	protected String getProfileUrl() {
		return "https://auth.globus.org/v2/oauth2/userinfo";
	}

	@Override
	protected String sendRequestForData(Token accessToken, String dataUrl) throws HttpException {
        final ProxyOAuthRequest request = new ProxyOAuthRequest(Verb.GET, dataUrl, this.proxyHost, this.proxyPort);
        if (this.connectTimeout != 0) {
            request.setConnectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
        }
        if (this.readTimeout != 0) {
            request.setReadTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
        }
        this.service.signRequest(accessToken, request);
		request.addHeader("Content-Type", "application/json");
        final Response response = request.send();
        final int code = response.getCode();
        final String body = response.getBody();
        if (code != 200) {
            throw new HttpException(code, body);
        }
        return body;
	}
	
	@Override
	protected UserProfile extractUserProfile(final String body) {
		GlobusProfile profile = new GlobusProfile();
		JsonNode json = JsonHelper.getFirstNode(body);
		if (json != null) {
			String username = JsonHelper.get(json, GlobusAttributesDefinition.USERNAME).toString().split("@")[0];
			profile.setId(username);
			profile.addAttribute(EurekaAttributesDefinition.USERNAME, username);
			profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, GlobusAttributesDefinition.FULLNAME));
			profile.addAttribute(EurekaAttributesDefinition.EMAIL, JsonHelper.get(json, GlobusAttributesDefinition.EMAIL));
		}
		return profile;
	}
}
