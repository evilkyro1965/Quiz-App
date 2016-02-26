package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuestionImage;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizQuestion;
import com.kyrosoft.quiz.entity.dto.QuizQuestionSearchCriteria;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/quiz-question")
@Repository
public class QuizQuestionRestController extends BaseRestController {

    @Autowired
    QuizQuestionService quizQuestionService;

    @Transactional
    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public QuizQuestion create(@RequestBody QuizQuestion question) throws ServiceException,
            DatabasePersistenceException {
        setUserOwnedId(question);
        quizQuestionService.save(question);

        List<QuestionImage> questionImageList = new ArrayList<QuestionImage>();
        if(question.getQuestionImageList()!=null){
            for(QuestionImage questionImage: question.getQuestionImageList()) {
                QuestionImage temp = entityManager.find(QuestionImage.class,questionImage.getId());
                temp.setQuestion(question);
                entityManager.merge(temp);
            }
        }
        return question;
    }

    @RequestMapping(value = "/update/{id}", method= RequestMethod.POST)
    public QuizQuestion update(@RequestBody QuizQuestion question,@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {
        QuizQuestion updating = quizQuestionService.get(question.getId());
        updating.setNo(question.getNo());
        updating.setQuestion(question.getQuestion());
        updating.setChoiceA(question.getChoiceA());
        updating.setChoiceB(question.getChoiceB());
        updating.setChoiceC(question.getChoiceC());
        updating.setChoiceD(question.getChoiceD());
        updating.setCorrectAnswer(question.getCorrectAnswer());

        quizQuestionService.update(updating);
        List<QuestionImage> questionImageList = new ArrayList<QuestionImage>();
        if(question.getQuestionImageList()!=null){
            for(QuestionImage questionImage: question.getQuestionImageList()) {
                QuestionImage temp = entityManager.find(QuestionImage.class,questionImage.getId());
                temp.setQuestion(updating);
                entityManager.merge(temp);
            }
        }
        return question;
    }

    @RequestMapping(value = "/delete/{id}", method= RequestMethod.POST)
    public void delete(@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizQuestionService.delete(id);
    }

    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public SearchResult<QuizQuestion> search(@RequestBody QuizQuestionSearchCriteria criteria) throws ServiceException,
            DatabasePersistenceException {

        SearchResult<QuizQuestion> searchResult = quizQuestionService.search(criteria);
        if(searchResult.getValues()!=null) {
            for(QuizQuestion quizQuestion : searchResult.getValues()) {
                quizQuestion.setChoiceImageList(null);
                quizQuestion.setQuestionImageList(null);
                quizQuestion.getQuiz().setQuestionList(null);
            }
        }

        return searchResult;
    }

    @RequestMapping(value = "/get/{id}", method= RequestMethod.POST)
    @Transactional
    public QuizQuestion get(@PathVariable Long id) throws ServiceException,
            DatabasePersistenceException {

        QuizQuestion quizQuestion = quizQuestionService.get(id);
        quizQuestion.setChoiceImageList(null);
        quizQuestion.getQuestionImageList().size();
        if(quizQuestion.getQuestionImageList()!=null) {
            for(QuestionImage questionImage:quizQuestion.getQuestionImageList()) {
            }
        }
        quizQuestion.getQuiz().setQuestionList(null);

        return quizQuestion;
    }

    @RequestMapping(value = "/upload-image", method= RequestMethod.POST)
    @Transactional
    public QuestionImage uploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws ServiceException,
            DatabasePersistenceException {
        String filename = getUploadedImageFilename(file.getOriginalFilename(),request);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(filename)));
                stream.write(bytes);
                stream.close();

                QuestionImage questionImage = new QuestionImage();
                questionImage.setImgSrc(file.getOriginalFilename());
                setUserOwnedId(questionImage);
                getEntityManager().persist(questionImage);
                String ret = questionImage.getId().toString() + ";" + questionImage.getImgSrc();
                return questionImage;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

}
