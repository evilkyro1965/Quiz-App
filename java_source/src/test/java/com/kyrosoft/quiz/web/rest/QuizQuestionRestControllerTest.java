package com.kyrosoft.quiz.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrosoft.quiz.entity.*;
import com.kyrosoft.quiz.entity.dto.QuizQuestionSearchCriteria;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
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
public class QuizQuestionRestControllerTest extends BaseTest {

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
    public void testCreateQuizQuestionRest() throws Exception {
        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz = createQuiz();

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setChoiceA("1");
        quizQuestion.setChoiceB("2");
        quizQuestion.setChoiceC("3");
        quizQuestion.setChoiceD("4");
        quizQuestion.setCorrectAnswer(MultipleChoiceAnswer.A);
        quizQuestion.setQuestion("Lorem ipsum");
        quizQuestion.setUserOwnedId(IdTest);

        quizQuestion.setNo(1);
        quizQuestion.setUserOwnedId(IdTest);
        quizQuestion.setQuiz(quiz);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz-question/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quizQuestion)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateQuizQuestionRest() throws Exception {
        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz = createQuiz();
        QuizQuestion quizQuestion = createQuizQuestion();
        quizQuestion.setQuestion("updated");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz-question/update/"+quizQuestion.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quizQuestion)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteQuizQuestionRest() throws Exception {
        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz = createQuiz();
        QuizQuestion quizQuestion = createQuizQuestion();

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz-question/delete/"+quizQuestion.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchQuizQuestionRest() throws Exception {
        createQuizCategory();
        createQuiz();
        createQuizQuestion();

        QuizQuestionSearchCriteria criteria = new QuizQuestionSearchCriteria();
        criteria.setQuizId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String criteriaJson = objectMapper.writeValueAsString(criteria);

        mockMvc.perform(post("/rest/quiz-question/search").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(criteriaJson).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
