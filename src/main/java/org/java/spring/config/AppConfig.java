package org.java.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * JAVA-BASED CONFIGURATION, BEAN SCOPES, @Value, PROFILES
 *
 * @Configuration : class that defines beans with @Bean methods (use for third-party classes you can't annotate).
 * @Component vs @Bean : @Component is class-level & auto-detected; @Bean is method-level & explicit.
 *
 * BEAN SCOPES:
 *   singleton (DEFAULT, one per container)   prototype (new each time)
 *   request, session, application, websocket (web only)
 *   Singleton beans must be stateless/thread-safe.
 *
 * @Value("${key:default}") : inject a property. @ConfigurationProperties : bind a group of properties.
 * Profiles: @Profile("dev") + spring.profiles.active=dev (application-dev.properties).
 * Properties order: command line > env vars > application-{profile}.properties > application.properties.
 *
 * BEAN LIFECYCLE: instantiate -> populate dependencies -> BeanNameAware/ApplicationContextAware
 *   -> @PostConstruct -> InitializingBean.afterPropertiesSet -> ready -> @PreDestroy -> DisposableBean.destroy.
 */
@Configuration
public class AppConfig {

    @Value("${app.name:Java Learning App}")
    private String appName;

    @Bean
    public AppInfo appInfo() { return new AppInfo(appName, "1.0.0"); }

    @Bean
    @Scope("prototype")
    public IdGenerator idGenerator() { return new IdGenerator(); }

    public record AppInfo(String name, String version) {}

    public static class IdGenerator {
        private static int counter = 0;
        public synchronized int next() { return ++counter; }
    }
}
