package com.bookross.mainservice.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@RequiredArgsConstructor
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
