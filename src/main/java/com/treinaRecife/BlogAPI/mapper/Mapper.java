package com.treinaRecife.BlogAPI.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


public interface Mapper<RequestDTO, Entidade, ResponseDTO> {
    Entidade requestDtoParaEntidade(RequestDTO requestDTO);
    ResponseDTO deEntidadeParaResponseDTO(Entidade entidade);
    Page<ResponseDTO> converterPaginaDeEntidadeParaResponseDTO(Page<Entidade> paginaDeEntidade);




}
