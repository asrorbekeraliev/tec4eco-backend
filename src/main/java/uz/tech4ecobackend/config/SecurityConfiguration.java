package uz.tech4ecobackend.config;

import uz.tech4ecobackend.security.JWTConfigure;
import uz.tech4ecobackend.security.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    /*  Lazy usuli
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService userDetailsService
    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //  default holatdagi ba'zi xavfsizlik blocklarini ochib chiqish
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
//*************************************************
                .authorizeRequests()

// ADMIN ga   /api/postdata/paging/ url ga ruxsat berish. Oxirida ** qo'yilsa url istalgancha cho'zilsa ham ruxsat beradi
//                .antMatchers("/api/postdata/paging/**").hasRole("ADMIN")
// USER ga va ADMIN ga    /api/posts  url ga ruxsat berish
//                .antMatchers("/api/posts").hasAnyRole("USER", "ADMIN")
// Barchaga (authenticated bo'lmaganlarga ham) biror linkka ruxsat berish. Masalan: register yoki login etc...
//                .antMatchers("/api/students/{id}").permitAll()
                .antMatchers("/api/**").permitAll()
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .apply(securityConfigureAdapter());

    }

    private JWTConfigure securityConfigureAdapter() {
        return new JWTConfigure(jwtTokenProvider);
    }
}
