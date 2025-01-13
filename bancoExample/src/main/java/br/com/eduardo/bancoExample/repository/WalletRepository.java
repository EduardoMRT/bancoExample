package br.com.eduardo.bancoExample.repository;

import br.com.eduardo.bancoExample.domain.User;
import br.com.eduardo.bancoExample.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUser(User user);

}
