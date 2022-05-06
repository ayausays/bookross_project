package com.bookross.mainservice.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @SequenceGenerator(name = "genre_sequence", sequenceName = "genre_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_sequence")
    private Long id;

    @Column(name = "genre")
    private String genre;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres", cascade = CascadeType.ALL)
    private List<Book> books;

}
