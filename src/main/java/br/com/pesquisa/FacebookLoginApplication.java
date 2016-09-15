package br.com.pesquisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSource.class})
@ComponentScan("br.com.pesquisa")
public class FacebookLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookLoginApplication.class, args);
    }
}
