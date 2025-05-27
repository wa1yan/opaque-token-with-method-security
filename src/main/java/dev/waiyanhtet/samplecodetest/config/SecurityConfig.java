package dev.waiyanhtet.samplecodetest.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final TokenResourceSeverConfig tokenResourceSeverConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(opaqueToken -> opaqueToken.introspector(customIntrospector())))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public OpaqueTokenIntrospector customIntrospector() {
        var introspector = SpringOpaqueTokenIntrospector
                .withIntrospectionUri(tokenResourceSeverConfig.getTokenCheckUri())
                .clientId(tokenResourceSeverConfig.getClientId())
                .clientSecret(tokenResourceSeverConfig.getClientSecret())
                .build();
        return token -> {
            log.info("Custom introspector used");
            var principal = introspector.introspect(token);

            String name = principal.getAttribute("user_name");
            List<String> authorities = principal.getAttribute("authorities");

            Set<GrantedAuthority> grantedAuthorities = null;

            if (authorities != null) {
                grantedAuthorities = authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());
            }

            return new DefaultOAuth2AuthenticatedPrincipal(
                    name,
                    principal.getAttributes(),
                    grantedAuthorities
            );
        };
    }
}
