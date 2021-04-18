package pl.sda.joined;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Tables;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "director_joined")
@PrimaryKeyJoinColumn(name = "id")
public class DirectorV3 extends EmployeeV3 {

    private String department;

    public DirectorV3(Long id, String firstName, String lastName, String department) {
        super(id, firstName, lastName);
        this.department = department;
    }

}
