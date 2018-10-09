package com.github.vinifkroth.cloudnative.tema5.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.vinifkroth.cloudnative.tema5.config.AppConfig;

public class AppContextSingleton {

	private static ApplicationContext appContext;

	public static synchronized ApplicationContext getInstance() {
		if (appContext == null)
			appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		return appContext;
	}
}
