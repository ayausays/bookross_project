package com.bookross.mainservice.demo.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {
    @SequenceGenerator(name = "blog_sequence", sequenceName = "blog_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "topic")
    private String topic;

    @Column(name = "blog_text")
    private String blogText;

    @Column(name = "dateOfPub")
    private LocalDateTime dateOfPublication;

    @Column(name = "blog_image_path")
    private String imagePath;
}
