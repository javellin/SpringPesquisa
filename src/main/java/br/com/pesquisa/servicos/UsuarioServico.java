package br.com.pesquisa.servicos;

import br.com.pesquisa.entidade.Usuario;
import br.com.pesquisa.entidade.enums.EAtivoInativo;
import br.com.pesquisa.entidade.enums.ETipoUsuario;
import br.com.pesquisa.exceptions.UsuarioJaCadastradoException;
import br.com.pesquisa.negocio.UsuarioNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yanpa on 01/09/2016.
 */
@RestController
@RequestMapping("/servicos/usuario")
public class UsuarioServico {

    @Autowired
    private UsuarioNegocio usuarioNegocio;

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public void registrar(@RequestBody Usuario usuario) throws UsuarioJaCadastradoException {
        usuario.setTipoUsuario(ETipoUsuario.COMUM);
        usuario.setSituacao(EAtivoInativo.ATIVO);
        usuarioNegocio.criarUsuario(usuario);
    }

    @RequestMapping(value = "/buscarUsuarioAtual", method = RequestMethod.GET)
    public Usuario buscarUsuarioAtual() {
        return usuarioNegocio.buscarUsuarioAtual();
    }

    @RequestMapping(value = "/buscarTiposUsuario", method = RequestMethod.GET)
    public ETipoUsuario[] buscarTiposUsuario() {
        return ETipoUsuario.values();
    }

    @RequestMapping(value = "/criarUsuario", method = RequestMethod.POST)
    public void criarUsuario(@RequestBody Usuario usuario) throws UsuarioJaCadastradoException {
        usuario.setSituacao(EAtivoInativo.ATIVO);
        usuarioNegocio.criarUsuario(usuario);
    }

}
