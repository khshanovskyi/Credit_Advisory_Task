package ua.khshanovskyi.task.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.khshanovskyi.task.entity.abstraction.SystemUser;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@ToString
public class Applicant extends SystemUser {

    @Column(nullable = false, unique = true,length = 60)
    private String ssn;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @ElementCollection(targetClass = PhoneNumber.class)
    @CollectionTable(name = "phone_applicants", joinColumns = @JoinColumn(name = "applicant_id"))
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    private List<Application> applications = new ArrayList<>();

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }
}
