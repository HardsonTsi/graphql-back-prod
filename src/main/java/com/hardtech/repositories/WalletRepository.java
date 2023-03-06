package com.hardtech.repositories;

import com.hardtech.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {
}