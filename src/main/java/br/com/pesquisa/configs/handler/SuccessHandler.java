package br.com.pesquisa.configs.handler;

import br.com.pesquisa.entidade.enums.ETipoUsuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanpa on 01/09/2016.
 */
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        User u = (User) authentication.getPrincipal();
        for (GrantedAuthority g : u.getAuthorities()) {
            if (ETipoUsuario.ADMIN.name().equals(g.getAuthority())) {
                httpServletResponse.sendRedirect("/#/admin");
            } else {
                httpServletResponse.sendRedirect("/#/comum");
            }
        }

    }
}