package com.collage.library.dto;

import com.collage.library.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDto {

    private Long iSubject;
    private Date date;
    private String name;
    private List<BookDtoPlain> listBooks;

    public static SubjectDto from(Subject subject){
        SubjectDto subjectDto=new SubjectDto();
        subjectDto.setISubject(subject.getISubject());
        subjectDto.setDate(subject.getDate());
        subjectDto.setName(subject.getName());
        if(Objects.nonNull(subject.getListBooks()))
            subjectDto.setListBooks(
                    subject.getListBooks()
                            .stream()
                            .map(BookDtoPlain::from)
                            .collect(Collectors.toList())
            );
        return subjectDto;
    }
}
