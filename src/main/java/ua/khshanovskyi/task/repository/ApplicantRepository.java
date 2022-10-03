package ua.khshanovskyi.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.khshanovskyi.task.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
