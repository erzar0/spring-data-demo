package spring.data.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "username" })
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
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

    // Validators may be used only when using constructor, not setters!
//    @Min(5)
//    @Max(30)
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="email")
    private String email;

//    @Min(8)
    @NotNull
    @Column(name="password")
    private String password;
}