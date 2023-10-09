package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequest;
import com.treinaRecife.BlogAPI.dto.request.ComentarioRequestMin;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.exceptions.EntidadeNotFoundException;
import com.treinaRecife.BlogAPI.mapper.ComentarioMapper;
import com.treinaRecife.BlogAPI.model.Comentario;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.model.Usuario;
import com.treinaRecife.BlogAPI.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    private final PostService postService;

    private final ComentarioMapper comentarioMapper;

    private final FazValidacoesService fazValidacoesService;

    public ComentarioResponse salvarComentariosToPostPeloIdPost(Long idPost, ComentarioRequestMin comentarioRequestMin) {
        var postEntidade = postService.returnPost(idPost);

       fazValidacoesService.checarSeReferenciaDeIdEValida(comentarioRequestMin.getIdAutor());

        var comentarioEntidade = comentarioMapper.requestDtoMinParaEntidade(comentarioRequestMin, idPost);

        comentarioEntidade.setPost(postEntidade);

        comentarioRepository.save(comentarioEntidade);

        return comentarioMapper.deEntidadeParaResponseDTO(comentarioEntidade);
    }

    public Page<ComentarioResponse> paginarComentariosByPostId(Long postId, Pageable pageable) {
        var postEntidade = postService.returnPost(postId);

        Page<ComentarioResponse> comentariosResponse;

        comentariosResponse = comentarioMapper.converterPaginaDeEntidadeParaResponseDTO(comentarioRepository.findComentarioByPostId(postId, pageable));

        return comentariosResponse;
    }

    public ComentarioResponse atualizarComentarioByIdComentario(Long idComentario, ComentarioRequest comentarioRequest) {
        var comentarioEntidade = returnComentario(idComentario);

        fazValidacoesService.checarSeDuasReferenciaDeIdSaoValidas(comentarioRequest.getIdAutor(), comentarioRequest.getIdPost());

        atualizarComentario(comentarioRequest, comentarioEntidade);

        return comentarioMapper.deEntidadeParaResponseDTO(comentarioRepository.save(comentarioEntidade));
    }

    public Page<ComentarioResponse> paginarComentarios(Pageable pageable) {
        return comentarioMapper.converterPaginaDeEntidadeParaResponseDTO(comentarioRepository.findAll(pageable));
    }

    public void deletarComentario(Long idComentario) {
        var comentarioEntidade = returnComentario(idComentario);

        comentarioRepository.deleteById(idComentario);
    }


    public Comentario returnComentario(Long idComentario) {
        return comentarioRepository.findById(idComentario).orElseThrow(() -> new EntidadeNotFoundException("Comentario com id " + idComentario + " Não existe"));
    }


    private void atualizarComentario(ComentarioRequest comentarioRequest, Comentario comentario) {
        var post = new Post();
        post.setIdPost(comentarioRequest.getIdPost());

        var autor = new Usuario();
        autor.setIdUsuario(comentarioRequest.getIdAutor());

        comentario.setPost(post);
        comentario.setAutor(autor);
        comentario.setTexto(comentarioRequest.getTexto());
    }




}
