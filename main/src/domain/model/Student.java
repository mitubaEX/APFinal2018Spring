package domain.model;

import domain.AbstractEntity;

public class Student extends AbstractEntity {
    Integer id;
    Double exam;
    Double assignments;
    Double miniexam;

    public Student(
            Integer id,
            Double exam,
            Double assignments,
            Double miniexam
    ) {
        this.id = id;
        this.exam = exam;
        this.assignments = assignments;
        this.miniexam = miniexam;
    }
}
