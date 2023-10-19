package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.exceptions.PaginaVaziaException;
import com.treinaRecife.BlogAPI.exceptions.ReferenciaInvalidaException;
import com.treinaRecife.BlogAPI.exceptions.UniqueException;
import com.treinaRecife.BlogAPI.repository.ComentarioRepository;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import com.treinaRecife.BlogAPI.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FazValidacoesService {

    private final ComentarioRepository comentarioRepository;

    private final PostRepository postRepository;

    private final AutorRepository autorRepository;

    public void checarSeReferenciaDeIdEValida(Long id) {
        if (autorRepository.findById(id).isEmpty()) {
            throw new ReferenciaInvalidaException("Id " + id + " que você está tentando referenciar não existe");

        }
    }

    public void checarSeEmailECpfJaExistemNoBancoDeDados(String email, String cpf){
        if(autorRepository.findByEmail(email).isPresent() || autorRepository.findByCpf(cpf).isPresent()){
            throw new UniqueException("Email/CPF já estão cadastrados no sistema");
        }

    }

    public void checarSeDuasReferenciaDeIdSaoValidas(Long idAutor, Long idPost){
        if(autorRepository.findById(idAutor).isEmpty()){
            throw new ReferenciaInvalidaException("Id " + idAutor + " do Autor que você está tentando referenciar não existe");
        } else if (postRepository.findById(idPost).isEmpty()) {
            throw new ReferenciaInvalidaException("Id " + idPost + " do Post que você está tentando referenciar não existe");
        }
    }

    public void checarSeUmaPaginaEstaVazia(Page<?> page){
        if(page.isEmpty()){
            throw new PaginaVaziaException("No momento a pagina que você procura está vazia");
        }
    }
}