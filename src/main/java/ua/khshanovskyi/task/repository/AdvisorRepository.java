package ua.khshanovskyi.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.khshanovskyi.task.entity.Advisor;

public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}
