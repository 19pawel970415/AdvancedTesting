package pl.sda.mappedsuperclass;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Movie extends BaseEntity {

    private String title;
    private String description;
    private String type;
}
