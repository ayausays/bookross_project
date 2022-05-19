package com.bookross.mainservice.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "usersAddedToFavs"})
@NoArgsConstructor
@Entity
@Table(name = "book")
@ToString(exclude = {"user", "usersAddedToFavs"})
public class Book {
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "status")
    private String status;

    @Column(name = "book_image_path")
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "books_genres", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "genre_id") })
    private List<Genre> genres;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favoriteBooks", cascade = CascadeType.DETACH)
    private Set<AppUser> usersAddedToFavs;
}
