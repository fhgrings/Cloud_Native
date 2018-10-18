package com.github.vinifkroth.cloudnative.tema7.config;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
	private static Twitter twitter;

	public static synchronized Twitter getTwitterInstance() throws TwitterException {
		if (twitter == null) {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setOAuthConsumerKey(System.getenv("OAUTH_CONSUMER_KEY"))
					.setOAuthConsumerSecret(System.getenv("OAUTH_CONSUMER_SECRET"))
					.setOAuthAccessToken(System.getenv("OAUTH_ACESSTOKEN"))
					.setOAuthAccessTokenSecret(System.getenv("OAUTH_ACESSTOKEN_SECRET"));
		}
		return twitter;
	}
}