package nttd.bootcamp.microservices.customer.management.customermanagement.repository;

import nttd.bootcamp.microservices.customer.management.customermanagement.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientManagementRepository extends ReactiveMongoRepository<Client, String> {

}
