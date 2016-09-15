package br.com.pesquisa.entidade.base;

import javax.persistence.*;

/**
 * Created by yanpa on 01/09/2016.
 */
@MappedSuperclass
public class EntidadeBase {

    @Id
    @Column( name = "ID" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    public Long getId ( ) {

        return id;
    }

    public void setId ( Long id ) {

        this.id = id;
    }
}
