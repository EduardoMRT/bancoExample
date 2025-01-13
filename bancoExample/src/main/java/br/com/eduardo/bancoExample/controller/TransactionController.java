package br.com.eduardo.bancoExample.controller;

import br.com.eduardo.bancoExample.domain.Wallet;
import br.com.eduardo.bancoExample.services.TransactionService;
import br.com.eduardo.bancoExample.services.WalletService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final WalletService walletService;

    public TransactionController(TransactionService transactionService, WalletService walletService) {
        this.transactionService = transactionService;
        this.walletService = walletService;
    }


    @PostMapping("/create/{payer}/{payee}/{value}")
    public String createTransaction(@RequestParam Long payeeId, @RequestParam Long payerId, @RequestParam BigDecimal value, HttpServletResponse response){

        if(walletService.findWalletById(payerId).isPresent() && walletService.findWalletById(payeeId).isPresent()){
            var payer = walletService.findWalletById(payerId).get();
            var payee = walletService.findWalletById(payeeId).get();

            if(transactionService.createTransaction(payee, payer, value)){
                response.setStatus(HttpStatus.CREATED.value());
            }
        }

       return "Sucess";
    }
}
