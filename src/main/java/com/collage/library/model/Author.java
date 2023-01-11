package com.collage.library.model;

import com.collage.library.dto.AuthorDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "author_tb")
public class Author implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize =1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    @Column(name = "id_author")
    private Long idAuthor;

    @NotNull
    private Date date;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "listAuthors",cascade = CascadeType.ALL)
    private List<Book> listBooks;

    public void addBook(Book book){
        this.listBooks.add(book);
    }

    public void removeBook(Book book){
        this.listBooks.remove(book);
    }

    public static Author from(AuthorDto authorDto){
        Author author=new Author();
        author.setDate(authorDto.getDate());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        return author;
    }

}
