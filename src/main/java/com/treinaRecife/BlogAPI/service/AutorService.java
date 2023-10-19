package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.AutorRequest;
import com.treinaRecife.BlogAPI.dto.response.AutorResponse;
import com.treinaRecife.BlogAPI.exceptions.EntidadeNotFoundException;
import com.treinaRecife.BlogAPI.integration.ViaCepClient;
import com.treinaRecife.BlogAPI.mapper.AutorMapper;
import com.treinaRecife.BlogAPI.model.Endereco;
import com.treinaRecife.BlogAPI.model.Autor;
import com.treinaRecife.BlogAPI.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorMapper autorMapper;

    private final FazValidacoesService fazValidacoesService;

    private final ViaCepClient viaCepClient;


    public AutorResponse salvarUsuario(AutorRequest autorRequest) {
        fazValidacoesService.checarSeEmailECpfJaExistemNoBancoDeDados(autorRequest.getEmail(),autorRequest.getCpf());

        var autorEntidade = autorMapper.requestDtoParaEntidade(autorRequest);

        var endereco = viaCepClient.getCep(autorRequest.getCep());

        if(autorRequest.getComplemento() != null){
            endereco.setComplemento(autorRequest.getComplemento());
        }

        autorEntidade.setEndereco(endereco);

        autorRepository.save(autorEntidade);

        return autorMapper.deEntidadeParaResponseDTO(autorEntidade);
    }



    public AutorResponse acharUsuarioPorId(Long idUsuario) {
        var usuarioEntidade = returnAutor(idUsuario);

        return autorMapper.deEntidadeParaResponseDTO(usuarioEntidade);
    }

    public Page<AutorResponse> paginarUsuarios(Pageable pageable) {
        fazValidacoesService.checarSeUmaPaginaEstaVazia(autorRepository.findAll(pageable));

        return autorMapper.converterPaginaDeEntidadeParaResponseDTO(autorRepository.findAll(pageable));
    }

    public AutorResponse atualizarUsuarioPorId(Long idUsuario, AutorRequest autorRequest) {
        var usuarioEntidade = returnAutor(idUsuario);

        atualizarDadosDoAutor(usuarioEntidade, autorRequest);

        return autorMapper.deEntidadeParaResponseDTO(autorRepository.save(usuarioEntidade));
    }

    public void deletarUsuarioPorId(Long idAutor) {
        var usuarioEntidade = returnAutor(idAutor);

        autorRepository.deleteById(usuarioEntidade.getIdAutor());
    }


    public Autor returnAutor(Long idAutor) {
        return autorRepository.findById(idAutor).orElseThrow(() -> new EntidadeNotFoundException("Autor com id " + idAutor + " Não encontrado"));
    }

    private void atualizarDadosDoAutor(Autor autorEntidade, AutorRequest autorRequest) {
        autorEntidade.setNome(autorRequest.getNome());
        autorEntidade.setSobreNome(autorRequest.getSobreNome());
        autorEntidade.setEmail(autorRequest.getEmail());
        autorEntidade.setCpf(autorRequest.getCpf());
        var endereco = checarComplementoDoDto(autorRequest);

        autorEntidade.setEndereco(endereco);
    }
    private Endereco checarComplementoDoDto(AutorRequest autorRequest){
        var endereco = viaCepClient.getCep(autorRequest.getCep());

        if(autorRequest.getComplemento() != null){
            endereco.setComplemento(autorRequest.getComplemento());
        }
        return endereco;
    }

}
