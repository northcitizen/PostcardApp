package com.example.webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "postcard")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"user"})
//@EqualsAndHashCode(exclude = {"user"})
public class Postcard {

    static final int MAX_COUNTRY_SIZE = 100;
    static final int MIN_COUNTRY_SIZE = 2;
    static final int POST_NUMBER_SIZE = 9;

    @Id
//    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    //@Column(name = "pid")//, updatable = false, nullable = false)
    private UUID pid;

    @Column
    @NotBlank(message = "Field 'post number' can not be blank")
    @Size(min = POST_NUMBER_SIZE, max = POST_NUMBER_SIZE)
    private String postNumber;

    @Column
    @NotBlank(message = "Field 'country' can not be blank")
    @Size(min = MIN_COUNTRY_SIZE, max = MAX_COUNTRY_SIZE)
    private String country;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @NotNull(message = "Field 'distance' can not be blank")
    private Long distance;

    @Column
    @Enumerated(EnumType.STRING)
    private PostcardStatus status;

    @Column
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime sendDate;

    @Column
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime receiveDate;

    @ManyToOne
    @JsonIgnore
    private User user;
}