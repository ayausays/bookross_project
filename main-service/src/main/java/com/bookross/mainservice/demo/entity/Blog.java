package com.bookross.mainservice.demo.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "usersAddedToFavs"})
@ToString(exclude = {"user", "usersAddedToFavs"})
@NoArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {
    @SequenceGenerator(name = "blog_sequence", sequenceName = "blog_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "topic")
    private String topic;

    @Column(name = "blog_text")
    private String blogText;

    @Column(name = "dateOfPub")
    private LocalDateTime dateOfPublication;

    @Column(name = "blog_image_path")
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favoriteBlogs", cascade = CascadeType.DETACH)
    private Set<AppUser> usersAddedToFavs;
}
