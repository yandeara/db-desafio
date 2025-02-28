package br.com.yandeara.voting.application.mapper;

import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MotionMapper {

    MotionMapper INSTANCE = Mappers.getMapper( MotionMapper.class );

    Motion toEntity(MotionRequest motionRequest);
    MotionResponse toResponse(Motion motion);

}
