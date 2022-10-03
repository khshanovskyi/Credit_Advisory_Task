package ua.khshanovskyi.task.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.khshanovskyi.task.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findApplicationByAmountUsdGreaterThanAndAmountUsdLessThanAndStatusIsAndAdvisorIsNull(
      BigDecimal amountUsd, BigDecimal amountUsd2, Application.Status status);
}
