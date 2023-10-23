package nttd.bootcamp.microservices.customer.management.customermanagement.controller;


import lombok.extern.slf4j.Slf4j;
import nttd.bootcamp.microservices.customer.management.customermanagement.dto.ClientDto;
import nttd.bootcamp.microservices.customer.management.customermanagement.service.ClientManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/api/v1/client")
@RestController
@Slf4j
public class ClientManagementController {

    @Autowired
    private final ClientManagementService clientManagementService;

    public ClientManagementController(ClientManagementService clientManagementService) {
        this.clientManagementService = clientManagementService;
    }

    @GetMapping("/{id}")
    public Mono<ClientDto> getClient(@PathVariable String id){
        return clientManagementService.getClient(id);
    }

    @PostMapping("/save")
    public Mono<ClientDto> saveClient(@RequestBody Mono<ClientDto> clientDtoMono){
        System.out.println("saving...");
        return clientManagementService.saveClient(clientDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ClientDto> updateClient(@RequestBody Mono<ClientDto> clientDtoMono , @PathVariable String id){
        return clientManagementService.updateClient(clientDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteClient(@PathVariable String id){
        return clientManagementService.deleteClient(id);
    }





}
