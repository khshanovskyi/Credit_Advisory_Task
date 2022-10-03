package ua.khshanovskyi.task.service;

import javax.transaction.Transactional;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.khshanovskyi.task.entity.Advisor;
import ua.khshanovskyi.task.entity.Application;
import ua.khshanovskyi.task.repository.AdvisorRepository;
import ua.khshanovskyi.task.repository.ApplicationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvisorService {

    private final AdvisorRepository advisorRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public Application assignApplicationByAdvisorId(Long advisorId) {
        log.debug("Start assigning process on advisor with id [{}]...", advisorId);
        var advisor = advisorRepository.findById(advisorId)
          .orElseThrow(() -> new IllegalStateException(
            String.format("Advisor by id [%s] is not found. Please provide existing advisor id!", advisorId)));

        var application = extractOldestUnassignedApplication(advisor);
        advisor.assignApplication(application);

        log.debug("Assigment process on advisor with id [{}] is successfully finished.", advisorId);
        return application;
    }

    private Application extractOldestUnassignedApplication(Advisor advisor) {
        return applicationRepository.findApplicationByAmountUsdGreaterThanAndAmountUsdLessThanAndStatusIsAndAdvisorIsNull(
            advisor.getMinCreditAmountForHandling(), advisor.getMaxCreditAmountForHandling(), Application.Status.NEW)
          .stream()
          .min(Comparator.comparing(Application::getCreatedAt))
          .orElseThrow(() -> new IllegalStateException(
            String.format("No application find for advisor [%s %s, role - %s]. Please check if system contains " +
              "suitable applications...", advisor.getFirstName(), advisor.getLastName(), advisor.getRole()))
          );
    }
}
