package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.UsuarioRequest;
import com.treinaRecife.BlogAPI.dto.response.UsuarioResponse;
import com.treinaRecife.BlogAPI.exceptions.EntidadeNotFoundException;
import com.treinaRecife.BlogAPI.integration.ViaCepClient;
import com.treinaRecife.BlogAPI.mapper.UsuarioMapper;
import com.treinaRecife.BlogAPI.model.Endereco;
import com.treinaRecife.BlogAPI.model.Usuario;
import com.treinaRecife.BlogAPI.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final FazValidacoesService fazValidacoesService;

    private final ViaCepClient viaCepClient;


    public UsuarioResponse salvarUsuario(UsuarioRequest usuarioRequest) {
        var usuarioEntidade = usuarioMapper.requestDtoParaEntidade(usuarioRequest);

        fazValidacoesService.checarSeEmailNaoEstaDuplicado(usuarioEntidade.getEmail());

        var endereco = viaCepClient.getCep(usuarioRequest.getCep());

        if(usuarioRequest.getComplemento() != null){
            endereco.setComplemento(usuarioRequest.getComplemento());
        }

        usuarioEntidade.setEndereco(endereco);

        usuarioRepository.save(usuarioEntidade);

        return usuarioMapper.deEntidadeParaResponseDTO(usuarioEntidade);
    }

    public UsuarioResponse acharUsuarioPorId(Long idUsuario) {
        var usuarioEntidade = returnUsuario(idUsuario);

        return usuarioMapper.deEntidadeParaResponseDTO(usuarioEntidade);
    }

    public Page<UsuarioResponse> paginarUsuarios(Pageable pageable) {
        fazValidacoesService.checarSeUmaPaginaEstaVazia(usuarioRepository.findAll(pageable));

        return usuarioMapper.converterPaginaDeEntidadeParaResponseDTO(usuarioRepository.findAll(pageable));
    }

    public UsuarioResponse atualizarUsuarioPorId(Long idUsuario, UsuarioRequest usuarioRequest) {
        var usuarioEntidade = returnUsuario(idUsuario);

        atualizarDadosDoUsuario(usuarioEntidade, usuarioRequest);

        return usuarioMapper.deEntidadeParaResponseDTO(usuarioRepository.save(usuarioEntidade));
    }

    public void deletarUsuarioPorId(Long idUsuario) {
        var usuarioEntidade = returnUsuario(idUsuario);

        usuarioRepository.deleteById(usuarioEntidade.getIdUsuario());
    }


    public Usuario returnUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new EntidadeNotFoundException("Usuario com id " + idUsuario + " Não encontrado"));
    }

    private void atualizarDadosDoUsuario(Usuario usuarioEntidade, UsuarioRequest usuarioRequest) {
        usuarioEntidade.setNome(usuarioRequest.getNome());
        usuarioEntidade.setSobreNome(usuarioRequest.getSobreNome());
        usuarioEntidade.setEmail(usuarioRequest.getEmail());
        usuarioEntidade.setSenha(usuarioRequest.getSenha());

        var endereco = checarComplementoDoDto(usuarioRequest);

        usuarioEntidade.setEndereco(endereco);
    }
    private Endereco checarComplementoDoDto(UsuarioRequest usuarioRequest){
        var endereco = viaCepClient.getCep(usuarioRequest.getCep());

        if(usuarioRequest.getComplemento() != null){
            endereco.setComplemento(usuarioRequest.getComplemento());
        }
        return endereco;
    }

}
