package pl.sda.mappedsuperclass;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity  {

    @Id
    protected int id;
}
