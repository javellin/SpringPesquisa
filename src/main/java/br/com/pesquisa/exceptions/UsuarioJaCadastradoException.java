package br.com.pesquisa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus ( value = HttpStatus.BAD_REQUEST, reason = "Usuário já cadastrado" )
public class UsuarioJaCadastradoException extends Exception {

}
