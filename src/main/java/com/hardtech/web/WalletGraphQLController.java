package com.hardtech.web;


import com.hardtech.dtos.TransferDTO;
import com.hardtech.dtos.WalletDTO;
import com.hardtech.entities.Currency;
import com.hardtech.entities.Wallet;
import com.hardtech.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class WalletGraphQLController {
    private final WalletService walletService;

    @MutationMapping
    public Wallet addWallet(@Argument WalletDTO wallet) {
        return walletService.addWallet(wallet);
    }

    @MutationMapping
    public List<Wallet> transfer(@Argument TransferDTO transfer) {
        return walletService.transfer(transfer);
    }

    @QueryMapping
    public Wallet walletById(@Argument String id) {
        return walletService.walletById(id);
    }

    @QueryMapping
    public List<Wallet> userWallets() {
        return walletService.userWallets();
    }

    @QueryMapping
    public List<Currency> currencies() {
        return walletService.currencies();
    }

    @QueryMapping
    public Boolean deleteWallet(@Argument String id) {
        return walletService.deleteWallet(id);
    }

}
