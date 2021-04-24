package pl.sda.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Book")
@SecondaryTable(name = "book_type")
public class Book {

    @Id
    private Integer id;

    private String title;

    @Column(table = "book_type")
    private String type;

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
