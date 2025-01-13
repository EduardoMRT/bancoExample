package br.com.eduardo.bancoExample.domain;

import br.com.eduardo.bancoExample.enumerators.UserEnum;
import jakarta.persistence.*;


@Entity
public record User(
        @Id
        Long id,
        String fullName,
        String cpf,
        String cnpj,
        String email,
        int age,
        UserEnum type

) {
    public User() {
        this(null, null, null, null, null, 0, null);
    }

}
