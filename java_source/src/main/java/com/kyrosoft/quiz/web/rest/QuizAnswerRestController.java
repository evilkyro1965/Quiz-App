package com.kyrosoft.quiz.web.rest;


import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizSession;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/quiz-answer")
public class QuizAnswerRestController extends BaseRestController {

    @Autowired
    QuizAnswerService quizAnswerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MultipleChoiceQuizAnswer create(@RequestBody MultipleChoiceQuizAnswer quizAnswer) throws ServiceException,
            DatabasePersistenceException {

        setUserOwnedId(quizAnswer);
        quizAnswerService.save(quizAnswer);
        return quizAnswer;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public MultipleChoiceQuizAnswer update(@RequestBody MultipleChoiceQuizAnswer quizAnswer, @PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizAnswerService.update(quizAnswer);
        return quizAnswer;
    }

    @RequestMapping(value = "/create-all", method = RequestMethod.POST)
    public void create(@RequestBody List<MultipleChoiceQuizAnswer> quizAnswerList) throws ServiceException,
            DatabasePersistenceException {

        if (quizAnswerList != null) {
            for (MultipleChoiceQuizAnswer quizAnswer : quizAnswerList) {
                MultipleChoiceQuizAnswer src = quizAnswerService.get(
                        quizAnswer.getQuizSession().getId(),
                        quizAnswer.getQuestion().getId());

                if (src != null) {
                    src.setAnswer(quizAnswer.getAnswer());
                    quizAnswerService.update(src);
                } else {
                    quizAnswerService.save(quizAnswer);
                }
            }
        }

    }

    @RequestMapping(value = "/create-all-complete", method = RequestMethod.POST)
    @Transactional
    public void complete(@RequestBody List<MultipleChoiceQuizAnswer> quizAnswerList) throws ServiceException,
            DatabasePersistenceException {

        if(quizAnswerList!=null && quizAnswerList.size()>0) {
            QuizSession quizSession = entityManager.find(QuizSession.class, quizAnswerList.get(0).getQuizSession().getId());
            quizSession.setCompleted(true);
            entityManager.merge(quizSession);
        }

        if (quizAnswerList != null) {
            for (MultipleChoiceQuizAnswer quizAnswer : quizAnswerList) {
                MultipleChoiceQuizAnswer src = quizAnswerService.get(
                        quizAnswer.getQuizSession().getId(),
                        quizAnswer.getQuestion().getId());

                if (src != null) {
                    src.setAnswer(quizAnswer.getAnswer());
                    quizAnswerService.update(src);
                } else {
                    quizAnswerService.save(quizAnswer);
                }
            }
        }

    }

    @RequestMapping(value = "/get-session-answers/{id}", method = RequestMethod.POST)
    public List<MultipleChoiceQuizAnswer> getSessionAnswers(@PathVariable Long id) throws ServiceException,
            DatabasePersistenceException {

        List<MultipleChoiceQuizAnswer> answers = quizAnswerService.getAnswerList(id);

        if(answers!=null) {
            for(MultipleChoiceQuizAnswer answer:answers) {
                answer.getQuizSession().setAnswers(null);
                answer.getQuestion().setChoiceImageList(null);
                answer.getQuestion().setQuestionImageList(null);
                answer.getQuestion().getQuiz().setQuestionList(null);
            }
        }

        return answers;
    }

}