package top.wisely.resourceserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.FilterChainProxy;

import javax.servlet.Filter;

@SpringBootApplication
public class ResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@Bean
	CommandLineRunner filters(Filter springSecurityFilterChain){
		return args -> {
			FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
			filterChainProxy.getFilterChains()
					.stream()
					.flatMap(chain -> chain.getFilters().stream())
					.forEach(filter -> System.out.println(filter.getClass()));
		};
	}

}
