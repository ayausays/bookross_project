package com.bookross.mainservice.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "support")
public class Support {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supportEmail")
    private String supportEmail;

    @Column(name = "supportType")
    private String supportType;

    @Column(name = "supportComment")
    private String supportComment;

}
