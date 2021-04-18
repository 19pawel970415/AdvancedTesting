package pl.sda.singletable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("OFFICE_EMP")
public class OfficeEmployeeV2 extends EmployeeV2 {

    private String skills;

    public OfficeEmployeeV2(Long id, String firstName, String lastName, String skills) {
        super(id, firstName, lastName);
        this.skills = skills;
    }
}
