package com.bankymono.tickzonebackend.Entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="ticket")
public class Ticket extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Cannot be blank")
    @Column(name = "ticket_name")
    private String ticketName;

    @NotBlank(message = "Cannot be blank")
    @Column(name = "ticket_price")
    private String ticketPrice;


    @Column(name = "purchased")
    private Boolean purchased = false;


    @Column(name = "unique_code", nullable = false)
    private String uniqueCode;

    @Column(name = "info")
    private String info;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    Event event;
}
