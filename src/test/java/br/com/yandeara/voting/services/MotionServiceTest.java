package br.com.yandeara.voting.services;

import br.com.yandeara.voting.application.mapper.MotionMapper;
import br.com.yandeara.voting.application.service.MotionServiceImpl;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MotionServiceTest {

    @Mock
    private MotionRepository motionRepository;

    @Mock
    private MotionMapper motionMapper;

    @InjectMocks
    private MotionServiceImpl motionServiceImpl;

    @Test
    public void create_validRequest_shouldBeSaved() {
        Motion motion = new Motion(1L, null);
        MotionResponse motionResponse = new MotionResponse(1L, null);

        when(motionRepository.save(any(Motion.class))).thenReturn(motion);

        when(motionMapper.toResponse(any(Motion.class))).thenReturn(motionResponse);
        when(motionMapper.toEntity(any(MotionRequest.class))).thenReturn(motion);

        MotionResponse motionResponseSaved = motionServiceImpl.create(new MotionRequest());

        assertNotNull(motionResponseSaved);
        assertNotNull(motionResponseSaved.getId());
    }

}