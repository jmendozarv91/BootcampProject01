package nttd.bootcamp.microservices.transaction.management.transactionmanagement.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.CreditPaymentDto;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.TransactionOperationDto;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.CreditCardTransaction;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.TransactionEntity;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.service.TransactionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/api/v1/transaction-management")
@RestController
@AllArgsConstructor
@Slf4j
public class TransactionController {

  private final TransactionService transactionService;

//  @PostMapping("/transaction")
//  public Mono<TransactionEntity> saveTransaction(@RequestBody TransactionEntity transactionEntity) {
//    System.out.println(transactionEntity);
//    return transactionService.saveTransaction(transactionEntity);
//  }

//  @PostMapping("/transaction/bank-account")
//  public Mono<BankAccountTransaction> saveBankTransaction(BankAccountTransaction transactionEntity) {
//    return transactionService.saveBankAccountTransaction(transactionEntity);
//  }
//
  @PostMapping("/transaction")
  public Mono<CreditCardTransaction> saveCreditCardTransaction(
      @RequestBody CreditCardTransaction transactionEntity) {
    System.out.println(transactionEntity);
    return transactionService.saveCreditCardTransaction(transactionEntity);
  }
//
//
//  @PostMapping("/transaction/credit")
//  public Mono<CreditTransaction> saveCreditTransaction(
//      CreditTransaction transactionEntity) {
//    return transactionService.saveCreditTransaction(transactionEntity);
//  }


  @PostMapping("/deposit")
  public Mono<TransactionEntity> transactionDeposit
      (@Validated @RequestBody TransactionOperationDto transactionOperationDto) {
    return transactionService.performTransaction("deposit", transactionOperationDto);
  }

  @PostMapping("/withdrawal")
  public Mono<TransactionEntity> transactionWithdrawal
      (@Validated @RequestBody TransactionOperationDto transactionOperationDto) {
    return transactionService.performTransaction("withdrawal", transactionOperationDto);
  }

  @PostMapping("/payment/{creditId}")
  public Mono<TransactionEntity> paymentCredit(@PathVariable String creditId,
      @RequestBody CreditPaymentDto creditPaymentDto) {
    return transactionService.paymentCredit(creditId, creditPaymentDto);
  }

  @PostMapping("/charge")
  public Mono<TransactionEntity> transactionCharge() {
    return null;
  }

}
