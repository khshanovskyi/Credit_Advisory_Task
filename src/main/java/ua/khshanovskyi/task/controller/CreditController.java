package ua.khshanovskyi.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.khshanovskyi.task.entity.Application;
import ua.khshanovskyi.task.service.AdvisorService;

@RestController
@RequestMapping("credit")
@RequiredArgsConstructor
@Slf4j
public class CreditController {

    private final AdvisorService advisorService;

    @PostMapping("{advisorId}/applications")
    public ResponseEntity<Application> assignApplication(@PathVariable Long advisorId){
        log.debug("Try to assign new application on advisor with id [{}]", advisorId);
        Application application = advisorService.assignApplicationByAdvisorId(advisorId);

        log.debug("Application with id [{}] is successfully assigned for advisor with id [{}]", application.getId(), advisorId);
        return ResponseEntity
          .ok(application);
    }
}
