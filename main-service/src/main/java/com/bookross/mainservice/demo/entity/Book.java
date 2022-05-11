package com.bookross.mainservice.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "status")
    private String status;

    @Column(name = "book_image_path")
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_genres", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "genre_id") })
    private List<Genre> genres;
}
