package nttd.bootcamp.microservices.creditservice.utils;

import nttd.bootcamp.microservices.creditservice.dto.CreditDto;
import nttd.bootcamp.microservices.creditservice.entity.Credit;
import org.springframework.beans.BeanUtils;

public class AppUtils {
    public static Credit entityToEntity(CreditDto creditDto){
        Credit credit = new Credit();
        BeanUtils.copyProperties(creditDto,credit);
        return credit;
    }

    public static CreditDto entityToDto(Credit credit){
        CreditDto creditDto = new CreditDto();
        BeanUtils.copyProperties(credit,creditDto);
        return creditDto;
    }
}
