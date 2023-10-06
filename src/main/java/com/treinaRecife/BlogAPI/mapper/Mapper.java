package com.treinaRecife.BlogAPI.mapper;

public interface Mapper<RequestDTO, Entidade, ResponseDTO> {
    Entidade requestDtoParaEntidade(RequestDTO requestDTO);
    ResponseDTO deEntidadeParaResponseDTO(Entidade entidade);



}
