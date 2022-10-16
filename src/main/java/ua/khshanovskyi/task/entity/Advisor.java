package ua.khshanovskyi.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.khshanovskyi.task.entity.abstraction.SystemUser;

@Entity
@Table(name = "advisors")
@Getter
@Setter
@ToString
public class Advisor extends SystemUser {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdvisorRole role;

    @OneToMany(mappedBy = "advisor")
    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    private List<Application> applications = new ArrayList<>();

    public BigDecimal getMinCreditAmountForHandling() {
        BigDecimal result = null;
        switch (this.role) {
            case ASSOCIATE -> result = BigDecimal.ONE;
            case PARTNER -> result = new BigDecimal(10_000);
            case SENIOR -> result = new BigDecimal(50_000);
        }
        return result;
    }

    public void assignApplication(Application application) {
        verifyForAssigment();
        application.assignTo(this);
        applications.add(application);
    }

    public BigDecimal getMaxCreditAmountForHandling() {
        BigDecimal result = null;
        switch (this.role) {
            case ASSOCIATE -> result = new BigDecimal(9_999);
            case PARTNER -> result = new BigDecimal(49_999);
            case SENIOR -> result = new BigDecimal(Long.MAX_VALUE);
        }
        return result;
    }

    private void verifyForAssigment() {
        if (hasAssignedApplication()) {
            throw new IllegalStateException(
              String.format("Adviser [%s %s] already has assigned application. Please choose another" +
              "adviser or try again when this advisor will be available.", firstName, lastName));
        }
    }

    private boolean hasAssignedApplication() {
        return applications.stream()
          .map(Application::getStatus)
          .anyMatch(status -> status.equals(Application.Status.ASSIGNED));
    }

    private enum AdvisorRole {
        ASSOCIATE, PARTNER, SENIOR
    }
}
