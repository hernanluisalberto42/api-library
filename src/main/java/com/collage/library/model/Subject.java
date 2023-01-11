package com.collage.library.model;

import com.collage.library.dto.SubjectDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "subject_tb",uniqueConstraints = @UniqueConstraint(
        name = "name",
        columnNames = "name"
))
public class Subject implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    @Column(name = "id_subject")
    private Long iSubject;

    @NotNull
    private Date date;

    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "idSubject",
            referencedColumnName = "id_subject"
    )
    private List<Book> listBooks;

    public void addBook(Book book){
        this.listBooks.add(book);
    }

    public void removeBook(Book book){
        this.listBooks.remove(book);
    }

    public static Subject from(SubjectDto subjectDto){
        Subject subject=new Subject();
        subject.setDate(subjectDto.getDate());
        subject.setName(subjectDto.getName());
        return subject;
    }

}
