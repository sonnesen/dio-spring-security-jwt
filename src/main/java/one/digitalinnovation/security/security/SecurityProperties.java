package one.digitalinnovation.security.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "security.config")
@RequiredArgsConstructor
@Getter
public class SecurityProperties {
    private final String prefix;
    private final String key;
    private final Long expiration;
}
