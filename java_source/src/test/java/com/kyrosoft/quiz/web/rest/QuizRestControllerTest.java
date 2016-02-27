package com.kyrosoft.quiz.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2/20/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"/RestTestConf.xml"})
@SqlGroup({ @Sql(scripts = "/sql/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)})
public class QuizRestControllerTest extends BaseTest {

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
    @WithMockUser(username = "test",roles={"LECTURER"})
    public void testCreateQuizRest() throws Exception {
        QuizCategory quizCategory = createQuizCategory();

        Quiz quiz =  new Quiz();
        quiz.setUserOwnedId(IdTest);
        quiz.setCategory(quizCategory);
        quiz.setName(stringTest);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quiz)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateQuizRest() throws Exception {
        createQuizCategory();
        Quiz quiz = createQuiz();
        quiz.setName("updated");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz/update/"+quiz.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quiz)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteQuizRest() throws Exception {
        createQuizCategory();
        Quiz quiz = createQuiz();

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rest/quiz/delete/"+quiz.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testSearchQuizRest() throws Exception {
        createQuizCategory();
        Quiz quiz = createQuiz();

        QuizSearchCriteria criteria = new QuizSearchCriteria();
        criteria.setUserId(IdTest);

        ObjectMapper objectMapper = new ObjectMapper();
        String criteriaJson = objectMapper.writeValueAsString(criteria);

        mockMvc.perform(post("/rest/quiz/search").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(criteriaJson).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
