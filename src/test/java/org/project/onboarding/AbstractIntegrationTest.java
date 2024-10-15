package org.project.onboarding;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        value = {
                WithSecurityContextTestExecutionListener.class,
                DbUnitTestExecutionListener.class})
@TestPropertySource("classpath:application-test.properties")
public abstract class AbstractIntegrationTest {

}
