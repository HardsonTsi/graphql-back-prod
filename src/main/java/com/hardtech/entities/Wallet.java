package com.hardtech.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wallet {
    @Id
    String id;
    Double balance;
    Long createAt;
    String userId;
    @ManyToOne
    Currency currency;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    @OrderBy(value = "id DESC")
    List<WalletTransaction> walletTransactions = new ArrayList<>();
}
