package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizQuestion;
import com.kyrosoft.quiz.entity.dto.QuizQuestionSearchCriteria;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/quiz-question")
@Transactional
public class QuizQuestionRestController extends BaseRestController {

    @Autowired
    QuizQuestionService quizQuestionService;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public QuizQuestion create(@RequestBody QuizQuestion question) throws ServiceException,
            DatabasePersistenceException {

        setUserOwnedId(question);
        quizQuestionService.save(question);
        return question;
    }

    @RequestMapping(value = "/update/{id}", method= RequestMethod.POST)
    public QuizQuestion update(@RequestBody QuizQuestion quizQuestion,@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizQuestionService.update(quizQuestion);
        return quizQuestion;
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

        return searchResult;
    }

    @RequestMapping(value = "/upload-image", method= RequestMethod.POST)
    public String uploadImage(@RequestParam("file") MultipartFile file) throws ServiceException,
            DatabasePersistenceException {
        String name = file.getName();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
