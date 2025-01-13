package br.com.eduardo.bancoExample.services;

import br.com.eduardo.bancoExample.domain.Transaction;

import br.com.eduardo.bancoExample.domain.Wallet;
import br.com.eduardo.bancoExample.exceptions.TransactionException;
import br.com.eduardo.bancoExample.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {

    private final UserService userService;
    private final WalletService walletService;
    private final TransactionRepository transactionRepository;

    public TransactionService(UserService userService, WalletService walletService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.walletService = walletService;
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal verifyTotalInTransactionsOnDay(Wallet wallet){
        List<Transaction> transactions = transactionRepository.findByDate(new Date());

        BigDecimal totalValue = new BigDecimal("0.00");
        for(Transaction transaction : transactions){
            totalValue = totalValue.add(transaction.value());
        }
        return totalValue;
    }

    public void verifyLimitTransaction(Wallet payer, BigDecimal value){
        BigDecimal totalValueInTransactionsOnDay = verifyTotalInTransactionsOnDay(payer);
        int typeOfUser = payer.user().type().getValue();

        final BigDecimal LIMIT_TYPE_1 = new BigDecimal("5000.00");
        final BigDecimal LIMIT_TYPE_2 = new BigDecimal("50000.00");
        final BigDecimal LIMIT_TYPE_3 = new BigDecimal("500000.00");

        if(payer.balance().compareTo(new BigDecimal("0.00")) < 0){
            throw new TransactionException("Sem saldo na conta");
        }else if(payer.balance().compareTo(value) < 0){
            throw new TransactionException("Valor insuficiente na conta");
        }
        else if (totalValueInTransactionsOnDay.compareTo(LIMIT_TYPE_1) > 0 && typeOfUser == 1) {
             throw new TransactionException("Limite atingido: R$5000,00 para usuários do tipo 1");
        }else if (totalValueInTransactionsOnDay.compareTo(LIMIT_TYPE_2) > 0 && typeOfUser != 2 && typeOfUser != 3) {
             throw new TransactionException("Limite atingido: R$50000,00 para usuários não autorizados");
        }else if (totalValueInTransactionsOnDay.compareTo(LIMIT_TYPE_3) > 0 && typeOfUser != 3) {
             throw new TransactionException("Limite atingido: R$500000,00 para usuários não autorizados");
        }

    }

    @Transactional
    public boolean createTransaction(Wallet payee, Wallet payer, BigDecimal value){

        // 1 - Verify rules transaction
        verifyLimitTransaction(payer, value);

        // 2 - Verify if payee isn't payer
        if(Objects.equals(payer.id(), payee.id())){
            throw new TransactionException("Não é possível transferir dinheiro para si mesmo");
        }

        // 3 - Init transaction
        payer.subtractToAccount(payer, value);
        payee.addToAccount(payee, value);

        // 4 - Save
        walletService.saveWallet(payer);
        walletService.saveWallet(payee);

        // 5 - Notify


        return true;
    }

}
