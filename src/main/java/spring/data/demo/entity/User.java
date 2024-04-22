package spring.data.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "username" })
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="firstname")
    private String firstName;

    @NotNull
    @Column(name="lastname")
    private String lastName;

    @Min(5)
    @Max(30)
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="email")
    private String email;

    @Min(8)
    @NotNull
    @Column(name="USR_PASSWORD")
    private String password;
}