package pl.sda.compositekeys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;

@Entity
@IdClass(AccountId.class)
public class Account {
    @Id
    @Column(length = 20)
    private String accountNumber;

    @Id
    @Column(length = 20)
    private String accountType;

    private BigDecimal amount;
}
