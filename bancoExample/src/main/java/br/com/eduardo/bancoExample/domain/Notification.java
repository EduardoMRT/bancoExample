package br.com.eduardo.bancoExample.domain;

import jakarta.persistence.*;

@Entity
public record Notification(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String message,
        boolean notificated,

        @OneToOne
        Transaction transaction

) {

}
