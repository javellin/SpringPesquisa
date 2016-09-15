package br.com.pesquisa.entidade;

import br.com.pesquisa.entidade.base.EntidadeBase;
import br.com.pesquisa.entidade.enums.EAtivoInativo;
import br.com.pesquisa.entidade.enums.ETipoUsuario;

import javax.persistence.*;

/**
 * Created by yanpa on 01/09/2016.
 */
@Entity
@Table(name = "TB_USUARIO")
public class Usuario extends EntidadeBase {

    @Column(name = "USUARIO", length = 100)
    private String usuario;

    @Column(name = "EMAIL", length = 500)
    private String email;

    @Column(name = "SENHA", length = 100)
    private String senha;

    @Column ( name = "SITUACAO", length = 20 )
    @Enumerated ( EnumType.STRING )
    private EAtivoInativo situacao;

    @Column(name = "TIPO_USUARIO", length = 20)
    @Enumerated(EnumType.STRING)
    private ETipoUsuario tipoUsuario;

    public EAtivoInativo getSituacao() {
        return situacao;
    }

    public void setSituacao(EAtivoInativo situacao) {
        this.situacao = situacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
