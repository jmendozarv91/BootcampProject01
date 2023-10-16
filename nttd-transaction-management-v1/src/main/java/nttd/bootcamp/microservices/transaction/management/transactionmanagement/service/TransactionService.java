package nttd.bootcamp.microservices.transaction.management.transactionmanagement.service;

import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.*;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.TransactionEntity;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.exceptions.InsufficientFundsException;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.exceptions.InvalidTransactionTypeException;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository.TransactionRepository;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository.client.AccountRepository;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository.client.CreditRepository;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditRepository creditRepository;


    @Transactional
    public Mono<TransactionEntity> performTransaction(String type, TransactionOperationDto transactionOperationDto){
        return accountRepository.findAccountById(transactionOperationDto.getAccountId())
                .flatMap(account -> {
                   if (type.equals("deposit")){
                       account.setBalance(account.getBalance() + transactionOperationDto.getAmount());
                   }else if (type.equals("withdrawal")){
                       if (account.getBalance()<transactionOperationDto.getAmount()){
                           return Mono.error(new InsufficientFundsException("Fondos insuficientes"));
                       }
                       account.setBalance(account.getBalance()-transactionOperationDto.getAmount());
                   }else {
                       return Mono.error(new InvalidTransactionTypeException("Tipo de transacción no válido"));
                   }

                   TransactionEntity transactionEntity = new TransactionEntity();
                   transactionEntity.setAccountId(transactionOperationDto.getAccountId());
                   transactionEntity.setType(type);
                   transactionEntity.setAmount(transactionOperationDto.getAmount());
                   transactionEntity.setTransactionDate(LocalDateTime.now());
                   return transactionRepository.save(transactionEntity)
                           .flatMap(savedTransaction -> accountRepository
                                   .modifyAccountBalance(transactionOperationDto.getAccountId(),account)
                                   .thenReturn(savedTransaction));
                });
    }


    @Transactional
    public Mono<TransactionEntity> paymentCredit(String creditId , CreditPaymentDto creditPaymentDto){
        return creditRepository.findCredit(creditId)
                .flatMap(creditDto -> {
                    System.out.println("aaaaaaaaaaaaaaa");
                    System.out.println(creditDto.toString());
                    if (creditDto.getPendingAmount()<creditPaymentDto.getAmount()){
                        return Mono.error(new IllegalArgumentException("Amount exceeds pending credit"));
                    }
                    System.out.println(creditDto.toString());
                    CreditBalanceDto creditBalanceDto = new CreditBalanceDto();
                    creditBalanceDto.setPendingAmount(creditDto.getPendingAmount() - creditPaymentDto.getAmount());
                    creditBalanceDto.setAmount(creditDto.getAmount() + creditPaymentDto.getAmount());
                    return creditRepository.refreshBalanceCredit(creditId,creditBalanceDto);
                })
                .flatMap(creditDto -> {
                    TransactionDto transactionDto = new TransactionDto();
                    transactionDto.setCreditId(creditId);
                    transactionDto.setAmount(creditPaymentDto.getAmount());
                    transactionDto.setTransactionDate(LocalDateTime.now());
                    transactionDto.setType("payment");
                    return transactionRepository.save(AppUtils.entityToEntity(transactionDto));
                });
    }









}
