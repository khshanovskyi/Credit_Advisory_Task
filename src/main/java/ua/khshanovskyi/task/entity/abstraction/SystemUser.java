package ua.khshanovskyi.task.entity.abstraction;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class SystemUser extends BaseEntity {

    @Column(nullable = false, length = 25)
    protected String firstName;

    @Column(nullable = false, length = 25)
    protected String lastName;

    @Column(nullable = false, unique = true, length = 30)
    protected String email;

    @Column(nullable = false, unique = true, length = 25)
    protected String cognitoUsername;
}
