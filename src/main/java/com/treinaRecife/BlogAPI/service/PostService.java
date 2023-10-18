package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponseWithComments;
import com.treinaRecife.BlogAPI.exceptions.EntidadeNotFoundException;
import com.treinaRecife.BlogAPI.mapper.PostMapper;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.model.Autor;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    private final AutorService autorService;

    private final FazValidacoesService fazValidacoesService;


    public PostResponse salvarPost(PostRequest postRequest) {
        fazValidacoesService.checarSeReferenciaDeIdEValida(postRequest.getIdAutor());

        var postEntidade = postMapper.requestDtoParaEntidade(postRequest);

        postRepository.save(postEntidade);

        return postMapper.deEntidadeParaResponseDTO(postEntidade);
    }

    public PostResponse acharPostPorId(Long idPost) {
        var postEntidade = returnPost(idPost);
        return postMapper.deEntidadeParaResponseDTO(postEntidade);
    }

    public Page<PostResponse> paginarPosts(Pageable pageable) {
        fazValidacoesService.checarSeUmaPaginaEstaVazia(postRepository.findAll(pageable));

        return postMapper.converterPaginaDeEntidadeParaResponseDTO(postRepository.findAll(pageable));
    }

    public PostResponse atualizarPostPorId(Long idPost, PostRequest postRequest) {
        var postEntidade = returnPost(idPost);

        fazValidacoesService.checarSeReferenciaDeIdEValida(postRequest.getIdAutor());

        atualizarDadosPost(postRequest, postEntidade);

        return postMapper.deEntidadeParaResponseDTO(postEntidade);
    }

    public void deletarPostPorId(Long idPost) {
        var postEntidade = returnPost(idPost);

        postRepository.deleteById(postEntidade.getIdPost());
    }

    public Page<PostResponse> acharPostByAutorId(Long idAutor, Pageable pageable) {
        var autorEntidade = autorService.returnAutor(idAutor);

        return postMapper.converterPaginaDeEntidadeParaResponseDTO(postRepository.findPostsByAutorId(idAutor, pageable));
    }

    public PostResponseWithComments retornarPostComComentarios(Long idPost) {
        var postEntidade = returnPost(idPost);
        return postMapper.converteDePostEntidadeParaPostComComentariosDTO(postEntidade);
    }


    //Metodo auxiliar
    public Post returnPost(Long idPost) {
        return postRepository.findById(idPost).orElseThrow
                (() -> new EntidadeNotFoundException("Post com id " + idPost + " Não encontrado"));
    }

    //Metodo Auxiliar
    private void atualizarDadosPost(PostRequest postRequest, Post postEntidade) {
        Autor autorEntidade = new Autor();
        autorEntidade.setIdAutor(postRequest.getIdAutor());

        postEntidade.setAutor(autorEntidade);
        postEntidade.setConteudo(postRequest.getConteudo());
        postEntidade.setTitulo(postRequest.getTitulo());

    }


}
