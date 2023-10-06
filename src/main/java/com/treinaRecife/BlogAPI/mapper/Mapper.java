package com.treinaRecife.BlogAPI.mapper;

import org.springframework.stereotype.Component;


public interface Mapper<RequestDTO, Entidade, ResponseDTO> {
    Entidade requestDtoParaEntidade(RequestDTO requestDTO);
    ResponseDTO deEntidadeParaResponseDTO(Entidade entidade);



}
