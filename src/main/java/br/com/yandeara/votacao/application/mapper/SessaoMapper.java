package br.com.yandeara.votacao.application.mapper;

import br.com.yandeara.votacao.domain.model.Sessao;
import br.com.yandeara.votacao.web.response.SessaoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessaoMapper {

    SessaoMapper INSTANCE = Mappers.getMapper( SessaoMapper.class );

    @Mapping(source = "pauta.id", target = "pautaId")
    SessaoResponse toDto(Sessao entity);

}
