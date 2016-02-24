package com.kyrosoft.quiz.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrosoft.quiz.entity.MultipleChoiceAnswer;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2/20/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"/RestTestConf.xml"})
@SqlGroup({ @Sql(scripts = "/sql/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)})
public class QuizAnswerRestControllerTest extends BaseTest {

    /**
     * Represents the web application context.
     */
    @Autowired
    private WebApplicationContext wac;

    /**
     * Represents the mock MVC.
     */
    private MockMvc mockMvc;

    /**
     * Sets up testing environment. It initializes the mock MVC.
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreateQuizAnswerRest() throws Exception {
        createUser();
        createQuizCategory();
        createQuiz();
        createUserQuizAnswer();

        MultipleChoiceQuizAnswer quizAnswer = new MultipleChoiceQuizAnswer();
        quizAnswer.setNo(1);
        quizAnswer.setAnswer(MultipleChoiceAnswer.A);
        quizAnswer.setUserQuizAnswer(this.userQuizAnswer);
        quizAnswer.setUserOwnedId(IdTest);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz-answer/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quizAnswer)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateQuizAnswerRest() throws Exception {
        createUser();
        createQuizCategory();
        createQuiz();
        createUserQuizAnswer();

        MultipleChoiceQuizAnswer quizAnswer = new MultipleChoiceQuizAnswer();
        quizAnswer.setNo(1);
        quizAnswer.setAnswer(MultipleChoiceAnswer.A);
        quizAnswer.setUserQuizAnswer(this.userQuizAnswer);
        quizAnswer.setUserOwnedId(IdTest);
        quizAnswerService.save(quizAnswer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz-answer/update/"+quizAnswer.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quizAnswer)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
