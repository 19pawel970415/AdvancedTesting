package pl.sda.compositekeys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountId implements Serializable {

    private String accountNumber;

    private String accountType;
}
