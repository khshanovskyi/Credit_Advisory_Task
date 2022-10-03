package ua.khshanovskyi.task.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "number")
public class PhoneNumber {

    @Column(nullable = false, unique = true)
    private Long number;

    @Enumerated
    @Column(nullable = false)
    private PhoneType phoneType;

    private enum PhoneType {
        HOME, WORK, MOBILE
    }
}
