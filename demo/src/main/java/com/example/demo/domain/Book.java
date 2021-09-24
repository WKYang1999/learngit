package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 32)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",joinColumns = {
            @JoinColumn(name = "book_id",referencedColumnName = "id")},inverseJoinColumns = {
            @JoinColumn(name = "author_id",referencedColumnName = "id")
    })
    private Set<Author> authors;

    public Book(){
        super();
    }
    public Book(String name){
        super();
        this.name = name;
        this.authors = new HashSet<>();
    }
    public Book(String name,Set<Author> authors){
        super();
        this.name = name;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return String.format("Book [id=%s,name=%s]",id,name);
    }
}
