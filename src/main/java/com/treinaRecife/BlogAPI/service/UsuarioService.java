package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.UsuarioRequest;
import com.treinaRecife.BlogAPI.dto.response.UsuarioResponse;
import com.treinaRecife.BlogAPI.mapper.UsuarioMapper;
import com.treinaRecife.BlogAPI.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;
    public UsuarioResponse salvarUsuario (UsuarioRequest usuarioRequest){
        var usuarioEntidade = usuarioMapper.requestDtoParaEntidade(usuarioRequest);

        usuarioRepository.save(usuarioEntidade);

        return usuarioMapper.deEntidadeParaResponseDTO(usuarioEntidade);

    }


}
