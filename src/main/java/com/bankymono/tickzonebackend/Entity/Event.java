package com.bankymono.tickzonebackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="event")
@RequiredArgsConstructor
public class Event extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Cannot be blank")
    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @NotBlank(message = "Cannot be blank")
    @Column(name = "event_location")
    private String eventLocation;

    @NotBlank(message = "Cannot be blank")
    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "publish")
    private Boolean publish = false;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL,targetEntity = Ticket.class)
    private List<Ticket> tickets;
}
