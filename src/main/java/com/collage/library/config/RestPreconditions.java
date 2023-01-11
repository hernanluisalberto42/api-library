package com.collage.library.config;

public class RestPreconditions {

    public static void checkIfYearIsGreaterThan1500(final Integer year ){
        if(year < 1500)
            throw new CustomNotFoundException("Year must be greater than 1500");
    }

    public static void checkIfNumberIsGreaterThanOne(final Integer number ){
        if(number < 2)
            throw new CustomNotFoundException("Book must have a number > 1");
    }

    public static void checkIfCountPagesGraterThanNine(final Integer countPages ){
        if(countPages < 2)
            throw new CustomNotFoundException("Book must have more 9 pages");
    }
}
