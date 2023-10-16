package nttd.bootcamp.microservices.customer.management.customermanagement.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import nttd.bootcamp.microservices.customer.management.customermanagement.dto.ClientDto;
import nttd.bootcamp.microservices.customer.management.customermanagement.entity.Client;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class AppUtils {

    public static ClientDto entityToDto(Client client){
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client,clientDto);
        return clientDto;
    }

    public static Client entityToEntity(ClientDto clientDto){
        Client client = new Client();
        BeanUtils.copyProperties(clientDto,client);
//        client.setId(UUID.randomUUID().toString());
        return client;
    }

    public static Client entityToEntity2(ClientDto clientDto){
        Client client = new Client();
        BeanUtils.copyProperties(clientDto,client);
        return client;
    }




}
