package com.hardtech.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long timestamp;
    Double amount;
    Double saleCurrencyPrice;
    Double purchaseCurrencyPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    Wallet wallet;
    @Enumerated(EnumType.STRING)
    TransactionType type;
}
