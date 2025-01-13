package br.com.eduardo.bancoExample.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
public record Wallet(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        @OneToOne User user,
        BigDecimal balance,
        Double creditRatio

) {
    public Wallet {

    }

    public Wallet subtractToAccount(Wallet wallet, BigDecimal value){
        return new Wallet(wallet.id, wallet.user, wallet.balance.subtract(value), wallet.creditRatio);
    }

    public Wallet addToAccount(Wallet wallet, BigDecimal value){
        return new Wallet(wallet.id, wallet.user, wallet.balance.add(value), wallet.creditRatio);
    }

}
