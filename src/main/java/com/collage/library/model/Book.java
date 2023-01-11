package com.collage.library.model;

import com.collage.library.dto.BookDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "book_tb",uniqueConstraints = @UniqueConstraint(
        name = "code",
        columnNames = "code"
))
public class Book implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "id_book")
    private Long idBook;

    @NotEmpty
    private String code;

    @NotEmpty
    private String title;

    @NotNull
    @Min(value = 1500)
    @Column(name = "year_edition")
    private Integer yearEdition;

    @NotEmpty
    private String editorial;

    @NotEmpty
    @Column(name = "country_book")
    private String countryFromBook;

    @NotEmpty
    private String resume;

    @NotNull
    @Min(value = 5)
    @Column(name = "count_pages")
    private Integer countPages;

    @Min(value = 1,message = "Book must have a number > 1")
    @Column(unique = true)
    private Integer number;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(
                    name ="idBook",
                    referencedColumnName = "id_Book"
            ),inverseJoinColumns = @JoinColumn(
                    name = "idAuthor",
            referencedColumnName = "id_author"
            )
    )
    private List<Author> listAuthors;

    public void addAuthor(Author author){
        this.listAuthors.add(author);
    }

    public void removeAuthor(Author author){
        this.listAuthors.remove(author);
    }

    public static Book from(BookDto bookDto){
        Book book=new Book();
        book.setCode(bookDto.getCodeDto());
        book.setTitle(bookDto.getTitleDto());
        book.setYearEdition(bookDto.getYearEditionDto());
        book.setEditorial(bookDto.getEditorialDto());
        book.setCountryFromBook(bookDto.getCountryFromBookDto());
        book.setResume(bookDto.getResumeDto());
        book.setCountPages(bookDto.getCountPagesDto());
        book.setNumber(bookDto.getNumberDto());
        return book;
    }

}
