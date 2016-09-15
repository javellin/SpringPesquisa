package br.com.pesquisa.configs;

import br.com.pesquisa.configs.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Created by yanpa on 01/09/2016.
 */
@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    public DriverManagerDataSource datasource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
        driverManagerDataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));
        return driverManagerDataSource;
    }

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        DataSource ds = datasource();
        auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new Md5PasswordEncoder()).usersByUsernameQuery("SELECT usuario, senha, case situacao when 'ATIVO' then 1 ELSE 0 END as situacao FROM tb_usuario WHERE usuario=?").authoritiesByUsernameQuery("SELECT usuario, tipo_usuario FROM tb_usuario WHERE usuario=?");
    }

    protected void configure(final HttpSecurity http) throws Exception {

        //@formatter:off
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/publico/**", "/resources/**", "/servicos/usuario/**", "/tags/**", "*.ico").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/comum/**").hasAuthority("COMUM")
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/auth/j_spring_security_check").successHandler(new SuccessHandler()).loginPage("/#/login").permitAll()
                .and().logout().logoutUrl("/auth/logout").permitAll()
                .and().logout().logoutSuccessUrl("/#/login?logout").permitAll()
                .and().csrf().disable();
        //@formatter:on

        //http.headers ( ).addHeaderWriter ( new SegurancaHeaderWriter ( ) );

        http.exceptionHandling().authenticationEntryPoint((request, response, authExcepion) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
    }
}
