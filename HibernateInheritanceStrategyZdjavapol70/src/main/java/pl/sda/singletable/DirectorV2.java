package pl.sda.singletable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("DIRECTOR")
public class DirectorV2 extends EmployeeV2 {

    private String department;

    public DirectorV2(Long id, String firstName, String lastName, String department) {
        super(id, firstName, lastName);
        this.department = department;
    }

}
