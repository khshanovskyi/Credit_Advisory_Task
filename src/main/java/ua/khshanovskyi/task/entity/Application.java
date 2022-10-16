package ua.khshanovskyi.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.khshanovskyi.task.entity.abstraction.BaseEntity;

@Entity
@Table(name = "applications")
@Getter
@Setter
@ToString
public class Application extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal amountUsd;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id")
    @ToString.Exclude
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    @ToString.Exclude
    private Advisor advisor;

    @Column(insertable = false)
    private LocalDateTime createdAt;

    private LocalDateTime assignedAt;

    private LocalDateTime resolvedAt;

    public enum Status {
        NEW, ASSIGNED, ON_HOLD, APPROVED, CANCELED, DECLINED
    }

    public void assignTo(Advisor advisor) {
        this.advisor = advisor;
        this.assignedAt = LocalDateTime.now();
        this.status = Status.ASSIGNED;
    }
}
