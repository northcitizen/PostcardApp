package com.example.webapp.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID user_id;

    @Column
    @NotBlank(message = "Field 'first name' can not be blank")
    private String firstName;

    @Column
    @NotBlank(message = "Field 'last name' can not be blank")
    private String lastName;

    @Column
    @Email
    private String email;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Postcard> postcards = new ArrayList<>();

}
