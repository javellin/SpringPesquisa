package br.com.pesquisa.negocio;

import br.com.pesquisa.entidade.Usuario;
import br.com.pesquisa.entidade.enums.EAtivoInativo;
import br.com.pesquisa.entidade.enums.ETipoUsuario;
import br.com.pesquisa.exceptions.UsuarioJaCadastradoException;
import br.com.pesquisa.repositorios.UsuarioRepositorio;
import br.com.pesquisa.util.helper.CriptografiaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by yanpa on 01/09/2016.
 */
@Component
public class UsuarioNegocio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private boolean verificarExistenciaUsuario(Usuario usuario) {
        Usuario usuarioUser;
        Usuario usuarioEmail;
        usuarioUser = usuarioRepositorio.findByUsuario(usuario.getUsuario());
        usuarioEmail = usuarioRepositorio.findByEmail(usuario.getEmail());

        if (usuarioUser == null && usuarioEmail == null) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario buscarUsuarioAtual() {
        return usuarioRepositorio.findByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void criarUsuario(Usuario usuario) throws UsuarioJaCadastradoException {
        if (verificarExistenciaUsuario(usuario)) {
            usuario.setSenha(CriptografiaHelper.criptografarMD5(usuario.getSenha()));
            usuarioRepositorio.save(usuario);
        } else {
            throw new UsuarioJaCadastradoException();
        }
    }
}
