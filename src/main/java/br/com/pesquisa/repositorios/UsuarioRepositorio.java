package br.com.pesquisa.repositorios;

import br.com.pesquisa.entidade.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yanpa on 01/09/2016.
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    Usuario findByUsuario(String usuario);

    Usuario findByEmail(String email);
}
