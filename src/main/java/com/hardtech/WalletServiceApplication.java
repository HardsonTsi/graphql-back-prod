package com.hardtech;

import com.hardtech.entities.TransactionType;
import com.hardtech.entities.Wallet;
import com.hardtech.entities.WalletTransaction;
import com.hardtech.repositories.CurrencyRepository;
import com.hardtech.repositories.WalletRepository;
import com.hardtech.repositories.WalletTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class WalletServiceApplication implements CommandLineRunner {

     CurrencyRepository currencyRepository;
     WalletRepository walletRepository;
    WalletTransactionRepository walletTransactionRepository;


    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
    public void loadDate() {
        Random random = new Random();

        //currency
        Stream.of("USD", "EUR", "XOF", "XAF", "AUD", "JPY", "GBP", "CAD", "NZD",
                        "CHF", "CNY", "RUB", "SEK", "NOK", "KRW", "MXN", "ZAR")
                .forEach(currencyName -> {
                    com.hardtech.entities.Currency currency = com.hardtech.entities.Currency.builder()
                            .code(currencyName)
                            .symbol(currencyName)
                            .name(currencyName)
                            .salePrice(random.nextDouble() * 200)
                            .purchasePrice(random.nextDouble() * 300)
                            .build();
                    currencyRepository.save(currency);
                });

        //wallet
        currencyRepository.findAll()
                .forEach(currency -> Stream.of("Trust Wallet", "Binance", "Perfect Money", "Klever")
                        .forEach(wallet -> {
                            Wallet wallett = Wallet.builder()
                                    .id(UUID.randomUUID().toString())
                                    .balance(random.nextDouble() * 1000)
                                    .userId(UUID.randomUUID().toString())
                                    .currency(currency)
                                    .build();
                            walletRepository.save(wallett);
                        }));

        //walletTransactions
        walletRepository.findAll()
                .forEach(wallet -> {
                    for (int i = 0; i < 10; i++) {
                        WalletTransaction walletTransaction = WalletTransaction.builder()
                                .amount(random.nextDouble() * 500)
                                .type(random.nextInt() * 10 > 5 ? TransactionType.CREDIT : TransactionType.DEBIT)
                                .wallet(wallet)
                                .timestamp(random.nextLong())
                                .build();
                        walletTransactionRepository.save(walletTransaction);
                    }
                });
        log.info("Enregistrement terminé");
    }


    @Override
    public void run(String... args) throws Exception {
        loadDate();
    }
}
