package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.application.mapper.MotionMapper;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import org.springframework.stereotype.Service;

@Service
public class MotionServiceImpl implements MotionService {

    private final MotionRepository motionRepository;
    private final MotionMapper motionMapper;

    public MotionServiceImpl(MotionRepository motionRepository, MotionMapper motionMapper) {
        this.motionRepository = motionRepository;
        this.motionMapper = motionMapper;
    }

    @Override
    public MotionResponse create(MotionRequest dto) {
        Motion motion = motionMapper.toEntity(dto);
        motion = motionRepository.save(motion);
        return motionMapper.toResponse(motion);
    }

}
