package com.example.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "address")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    static final int MAX_COUNTRY_SIZE = 100;
    static final int MIN_COUNTRY_SIZE = 2;
    static final int POST_NUMBER_SIZE = 6;

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    @NotBlank(message = "Field 'building' can not be blank")
    private String building;

    @Column
    @NotBlank(message = "Field 'street' can not be blank")
    private String street;

    @Column
    @NotBlank(message = "Field 'city' can not be blank")
    private String city;

    @Column
    @NotBlank(message = "Field 'post number' can not be blank")
    @Size(min = POST_NUMBER_SIZE, max = POST_NUMBER_SIZE)
    private String postNumber;

    @Column
    @NotBlank(message = "Field 'country' can not be blank")
    @Size(min = MIN_COUNTRY_SIZE, max = MAX_COUNTRY_SIZE)
    private String country;

    @Column
    private boolean active;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
