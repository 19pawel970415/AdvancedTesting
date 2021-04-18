package pl.sda.joined;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "office_emp_joined")
@PrimaryKeyJoinColumn(name = "id")
public class OfficeEmployeeV3 extends EmployeeV3 {

    private String skills;

    public OfficeEmployeeV3(Long id, String firstName, String lastName, String skills) {
        super(id, firstName, lastName);
        this.skills = skills;
    }
}
