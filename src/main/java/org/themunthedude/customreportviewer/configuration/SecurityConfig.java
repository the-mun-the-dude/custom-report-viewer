package org.themunthedude.customreportviewer.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security Configuration - LDAP and HTTP Authorizations.
 */
@Configuration
@ImportResource({ "secConfig.xml" }) //=> uncomment to use equivalent xml config
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}
