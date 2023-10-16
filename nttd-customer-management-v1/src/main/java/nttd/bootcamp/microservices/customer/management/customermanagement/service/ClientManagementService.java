package nttd.bootcamp.microservices.customer.management.customermanagement.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nttd.bootcamp.microservices.customer.management.customermanagement.dto.ClientDto;
import nttd.bootcamp.microservices.customer.management.customermanagement.repository.ClientManagementRepository;
import nttd.bootcamp.microservices.customer.management.customermanagement.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ClientManagementService {

    @Autowired
    private ClientManagementRepository repository;

    public Flux<ClientDto> getClients() {
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ClientDto> getClient(String id) {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<ClientDto> saveClient(Mono<ClientDto> clientDtoMono) {
        return clientDtoMono.map(AppUtils::entityToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ClientDto> updateClient(Mono<ClientDto> clientDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p -> clientDtoMono.map(AppUtils::entityToEntity2))
                .doOnNext(e -> e.setId(String.valueOf(UUID.fromString(id))))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteClient(String id){
        return repository.deleteById(id);
    }

}
