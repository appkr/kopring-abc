package kopring.abc.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Optional

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(basePackages = ["kopring.abc.repository"])
class JpaConfiguration {

    @Bean
    fun auditorAware(): AuditorAware<String> {
        return object : AuditorAware<String> {
            override fun getCurrentAuditor(): Optional<String> {
                return Optional.of("UNKNOWN")
            }
        }
    }
}
