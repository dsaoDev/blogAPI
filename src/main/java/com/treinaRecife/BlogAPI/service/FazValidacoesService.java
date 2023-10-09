package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.exceptions.EmailDuplicadoException;
import com.treinaRecife.BlogAPI.exceptions.ReferenciaInvalidaException;
import com.treinaRecife.BlogAPI.repository.ComentarioRepository;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import com.treinaRecife.BlogAPI.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FazValidacoesService {

    private final ComentarioRepository comentarioRepository;

    private final PostRepository postRepository;

    private final UsuarioRepository usuarioRepository;

    public void checarSeReferenciaDeIdEValida(Long id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ReferenciaInvalidaException("Id " + id + " que você está tentando referenciar não existe");

        }
    }

    public void checarSeEmailNaoEstaDuplicado(String email){
        if(usuarioRepository.findByEmail(email).isPresent()){
            throw new EmailDuplicadoException("Email já cadastrado no sistema");
        }
    }

    public void checarSeDuasReferenciaDeIdSaoValidas(Long idAutor, Long idPost){
        if(usuarioRepository.findById(idAutor).isEmpty()){
            throw new ReferenciaInvalidaException("Id " + idAutor + " do Autor que você está tentando referenciar não existe");
        } else if (postRepository.findById(idPost).isEmpty()) {
            throw new ReferenciaInvalidaException("Id " + idPost + " do Post que você está tentando referenciar não existe");
        }
    }
}