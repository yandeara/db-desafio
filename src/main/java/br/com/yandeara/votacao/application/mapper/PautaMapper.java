package br.com.yandeara.votacao.application.mapper;

import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper( PautaMapper.class );

    Pauta toEntity(PautaRequest dto);
    PautaResponse toDto(Pauta entity);

}
