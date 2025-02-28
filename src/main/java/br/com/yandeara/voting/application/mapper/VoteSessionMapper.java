package br.com.yandeara.voting.application.mapper;

import br.com.yandeara.voting.domain.model.VoteSession;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteSessionMapper {

    VoteSessionMapper INSTANCE = Mappers.getMapper( VoteSessionMapper.class );

    @Mapping(source = "motion.id", target = "motionId")
    VoteSessionResponse toResponse(VoteSession voteSession);

}
