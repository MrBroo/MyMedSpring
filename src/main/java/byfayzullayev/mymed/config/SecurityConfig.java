package byfayzullayev.mymed.config;


import byfayzullayev.mymed.filter.JwtTokenFilter;
import byfayzullayev.mymed.service.user.ApplicationUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserDetailService applicationUserDetailService;
    private final JwtTokenFilter jwtTokenFilter;


    @Autowired
    public SecurityConfig(ApplicationUserDetailService applicationUserDetailService, JwtTokenFilter jwtTokenFilter) {
        this.applicationUserDetailService = applicationUserDetailService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailService).passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/myMed/user/*").permitAll()
                .antMatchers("/api/myMed/category/list").permitAll()
                .antMatchers("/api/myMed/category/inSale/*").permitAll()
                .antMatchers("/api/myMed/category/inSale/sale/*").permitAll()
                .antMatchers("/api/myMed/category/reviews/*").permitAll()
                .antMatchers("/api/myMed/category/inNews/*").permitAll()
                .antMatchers("/api/myMed/category/inNews/news/*").permitAll()
                .antMatchers("/api/myMed/category/categoryServices/*").permitAll()
                .antMatchers("/api/myMed/clients/*").permitAll()
                .antMatchers("/api/myMed/category/categoryServices/services/*").permitAll()
                .antMatchers("/api/myMed/category/categorySpecialist/*").permitAll()
                .antMatchers("/api/myMed/category/inSpecialist/*").permitAll()
                .antMatchers("/api/myMed/category/inSpecialist/specialist/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}