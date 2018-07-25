package domain;

public abstract class AbstractEntity implements Entity {
  private Integer id;

  public boolean equals(Object that) {
    return id.equals(((AbstractEntity) that).id);
  }
}
