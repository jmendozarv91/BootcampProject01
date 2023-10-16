package nttd.bootcamp.microservices.transaction.management.transactionmanagement.utils;

import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.TransactionDto;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.TransactionEntity;
import org.springframework.beans.BeanUtils;

public class AppUtils {
    public static TransactionEntity entityToEntity(TransactionDto transactionDto){
        TransactionEntity transactionEntity = new TransactionEntity();
        BeanUtils.copyProperties(transactionDto,transactionEntity);
        return transactionEntity;
    }
}
