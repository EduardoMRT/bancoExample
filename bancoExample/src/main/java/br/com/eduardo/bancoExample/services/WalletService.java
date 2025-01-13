package br.com.eduardo.bancoExample.services;

import br.com.eduardo.bancoExample.domain.Wallet;
import br.com.eduardo.bancoExample.repository.UserRepository;
import br.com.eduardo.bancoExample.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public Optional<Wallet> getWalletFromUserByCpf(String cpf){
        return Optional.ofNullable(walletRepository.findByUser(userRepository.findByCpf(cpf)));
    }

    public Optional<Wallet> findWalletById(Long id){
        return walletRepository.findById(id);
    }

    public void saveWallet(Wallet wallet){
        walletRepository.save(wallet);
    }

}
