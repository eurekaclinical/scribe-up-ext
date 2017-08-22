package org.eurekaclinical.scribeupext.service;

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
 * 
 * 20160222 - Stephen Granite (sgranite@jhu.edu) - added redirect_uri parameter to authorization request
 * This was done to resolve a change in Globus Authenticaton that required the redirect to be in the 
 * access token call as well
 */
import com.fasterxml.jackson.databind.JsonNode;
import org.eurekaclinical.scribeupext.GlobusApi;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthRequest;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;
import org.scribe.up.profile.JsonHelper;

/**
 * Gets an access token for an authorized user.
 * 
 * @author Andrew Post
 */
public class GlobusOAuth20ServiceImpl extends OAuth20ServiceImpl {

    protected final DefaultApi20 api;
    protected final OAuthConfig config;
    protected final String proxyHost;
    protected final int proxyPort;

    /**
     * Instantiates this class with an OAuth API instance, query parameters,
     * and a proxy host and port, if any.
     * 
     * @param api the access token request URL to use. 
     * @param config query parameters for requesting an access token.
     * @param proxyHost the proxy host, if any. Can be <code>null</code>.
     * @param proxyPort the proxy port. Ignored if there is not a proxy host.
     */
    public GlobusOAuth20ServiceImpl(GlobusApi api, OAuthConfig config,
            String proxyHost, int proxyPort) {
        super(api, config);
        this.api = api;
        this.config = config;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    /**
     * Makes the call to request an access token for an authorized user.
     * 
     * @param requestToken not used.
     * @param verifier the code query parameter that Globus included in the
     * redirect after the authorization call.
     * 
     * @return the access token.
     */
    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier) {
        OAuthRequest request
                = new ProxyOAuthRequest(this.api.getAccessTokenVerb(),
                        this.api.getAccessTokenEndpoint(), this.proxyHost, this.proxyPort);
        String userpass = this.config.getApiKey() + ":" + this.config.getApiSecret();
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
        request.addHeader("Authorization", basicAuth);
        request.addBodyParameter("grant_type", "authorization_code");
        request.addBodyParameter("code", verifier.getValue());
        request.addBodyParameter("redirect_uri", config.getCallback());
        Response response = request.send();
        String body = response.getBody();
        JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            return new Token((String) JsonHelper.get(json, "access_token"), "", body);
        } else {
            return null;
        }

    }

    /**
     * Adds an authorization header containing the bearer token to a request.
     * It is intended for use in API calls after getting the access token, such
     * as requesting the user's profile.
     * 
     * @param accessToken the user's access token.
     * @param request the requests.
     */
    @Override
    public void signRequest(Token accessToken, OAuthRequest request) {
        request.addHeader("Authorization", "Bearer " + accessToken.getToken());
    }
}
