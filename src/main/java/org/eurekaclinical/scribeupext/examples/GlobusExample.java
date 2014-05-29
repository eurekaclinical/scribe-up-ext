package org.eurekaclinical.scribeupext.examples;

/*
 * #%L
 * ScribeUP Extensions
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.eurekaclinical.scribeupext.provider.GlobusProvider;
import org.scribe.up.credential.OAuthCredential;
import org.scribe.up.profile.UserProfile;

/**
 *
 * @author Andrew Post
 */
public class GlobusExample {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		GlobusProvider provider = new GlobusProvider();
		provider.setKey("your key");
		provider.setSecret("your secret");
		provider.setCallbackUrl("your callback url");

		System.out.println("=== Globus' OAuth Workflow ===");
		System.out.println();

		System.out.println("Paste this URL into your web browser:");
		System.out.println(provider.getAuthorizationUrl(null));
		System.out.println("And paste below the value of the code parameter in the callback URL after you authenticate with Globus");
		System.out.print(">>");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String verifier = URLDecoder.decode(br.readLine(), "UTF-8");
		System.out.println();

		// Trade the Request Token and Verifier for the Access Token
		System.out.println("Get user's OAuth credential...");
		OAuthCredential credential = new OAuthCredential(null, null, verifier, provider.getType());
		System.out.println("Credential is " + credential);
		System.out.println();

		// Now, get the user's profile (access token is retrieved behind the scenes)
		UserProfile userProfile = provider.getUserProfile(credential);
		System.out.println("The user's profile is:");
		System.out.println(userProfile.getAttributes());
	}
}
