package com.tdtech.scorecardapi;

import com.tdtech.scorecardapi.exceptions.ExceptionHandlers;
import com.tdtech.scorecardapi.exceptions.ResourceNotFoundException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExceptionHandlerTests {

    @Test
    void shouldSetValuesForResourceNotFoundException() {
        ResourceNotFoundException rnfe = new ResourceNotFoundException("abc123","testResource");
        assertThat(rnfe.getResourceName()).isEqualTo("testResource");
        assertThat(rnfe.getMessage()).contains("abc123");
    }

    @Test
    void handlerShouldContainProperMessageAndResourceName() {
        ResourceNotFoundException rnfe = new ResourceNotFoundException("abc123","testResource");
        ExceptionHandlers handlers = new ExceptionHandlers();

        assertThat(handlers.postNotFound(rnfe).getBody()).contains("abc123");
        assertThat(handlers.postNotFound(rnfe).getBody()).contains("testResource");
    }
}
