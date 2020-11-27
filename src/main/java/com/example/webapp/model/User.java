package com.example.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"postcards"})
//@EqualsAndHashCode(exclude = {"postcards"})
public class User {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    @NotBlank(message = "Field 'first name' can not be blank")
    private String firstName;

    @Column
    @NotBlank(message = "Field 'last name' can not be blank")
    private String lastName;

    @Column
    @Email
    private String email;

    @OneToMany(mappedBy = "user",//in case of targetEntity = Postcard.class, - can not deleted Parent
            //and creates table postcard-users
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Postcard> postcards;

}
