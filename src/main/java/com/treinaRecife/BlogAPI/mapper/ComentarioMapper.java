package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequest;
import com.treinaRecife.BlogAPI.dto.request.ComentarioRequestMin;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.model.Comentario;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ComentarioMapper implements Mapper<ComentarioRequest, Comentario, ComentarioResponse> {



    @Override
    public Comentario requestDtoParaEntidade(ComentarioRequest comentarioRequest) {
        var autorEntidade = new Usuario();
        autorEntidade.setIdUsuario(comentarioRequest.getIdAutor());

        var postEntidade = new Post();
        postEntidade.setIdPost(comentarioRequest.getIdPost());

        return Comentario.builder()
                .autor(autorEntidade)
                .post(postEntidade)
                .texto(comentarioRequest.getTexto())
                .build();
    }

    public Comentario requestDtoMinParaEntidade(ComentarioRequestMin comentarioRequestMin, Long idPost){
        var autorEntidade = new Usuario();
        autorEntidade.setIdUsuario(comentarioRequestMin.getIdAutor());

        var postEntidade = new Post();
        postEntidade.setIdPost(idPost);

        return Comentario.builder()
                .autor(autorEntidade)
                .post(postEntidade)
                .texto(comentarioRequestMin.getTexto())
                .build();
    }

    @Override
    public ComentarioResponse deEntidadeParaResponseDTO(Comentario comentario) {
        return new ComentarioResponse(comentario);
    }



    public List<ComentarioResponse> listConverterDeComentarioEntidadeParaListaDTO(List<Comentario> listComentarios) {
        return listComentarios.stream().map(this::deEntidadeParaResponseDTO).toList();

    }
    @Override
    public Page<ComentarioResponse> converterPaginaDeEntidadeParaResponseDTO(Page<Comentario> paginaDeEntidade) {
       return  paginaDeEntidade.map(this::deEntidadeParaResponseDTO);
    }

    public static ComentarioResponse deEntidadeParaResponseDTOStaticVersion(Comentario comentario) {
        return new ComentarioResponse(comentario);
    }




}
