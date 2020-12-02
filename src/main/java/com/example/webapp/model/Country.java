package com.example.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "country")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    static final int MAX_COUNTRY_SIZE = 100;
    static final int MIN_COUNTRY_SIZE = 2;

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    @NotBlank(message = "Field 'country' can not be blank")
    @Size(min = MIN_COUNTRY_SIZE, max = MAX_COUNTRY_SIZE)
    private String country;

    @Column
    @NotNull(message = "Field 'Cards number' can not be blank")
    private Long cardsNumber;

    @Column
    @NotNull(message = "Field 'Total distance' can not be blank")
    private Long totalDistance;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
