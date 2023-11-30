package com.demo.smartjob.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "cityCode")
    private String cityCode;

    @Column(name = "countryCode")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
