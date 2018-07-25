package domain.model;

import domain.AbstractEntity;

public class Result extends AbstractEntity {
    Integer id;
    Double finalScore;
    String grade;

    public Result(
            Integer id,
            Double finalScore,
            String grade
    ) {
        this.id = id;
        this.finalScore = finalScore;
        this.grade = grade;
    }
}
