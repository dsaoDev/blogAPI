package com.treinaRecife.BlogAPI.integration;

import com.treinaRecife.BlogAPI.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ViaCepClient", url = "https://viacep.com.br/")
public interface ViaCepClient {

    @GetMapping("ws/{cep}/json/")
    Endereco getCep(@PathVariable("cep") String cep);
}
