package br.com.eduardo.bancoExample.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public record Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @OneToOne
        @JoinColumn(name = "payee_id", referencedColumnName = "id")
        Wallet payee,
        @OneToOne
        @JoinColumn(name = "payer_id", referencedColumnName = "id")
        Wallet payer,
        BigDecimal value,

        Date date

) {
}
