package br.com.yandeara.voting.application.mapper;

import br.com.yandeara.voting.domain.dto.VoteCountDto;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.Vote;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import br.com.yandeara.voting.web.response.VoteCountResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper( VoteMapper.class );

    Vote toEntity(VoteRequest voteRequest);

    @Mapping(source = "motion.id", target = "motionId")
    VoteResponse toResponse(Vote vote);

    VoteCountResponse toCountResponse(VoteCountDto voteCountDto);

}
