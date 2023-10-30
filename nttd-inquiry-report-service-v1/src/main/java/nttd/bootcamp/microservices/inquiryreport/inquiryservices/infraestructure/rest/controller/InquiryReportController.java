package nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.services.InquiryReportManagementServices;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AverageBalanceSummaryResponse;
import org.openapitools.api.ReportsApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class InquiryReportController implements ReportsApi {

  private final InquiryReportManagementServices inquiryReportManagementServices;

  @Override
  public Mono<ResponseEntity<AverageBalanceSummaryResponse>> generateAverageBalanceSummary(
      String clientId, ServerWebExchange exchange) {
    return inquiryReportManagementServices.generateAverageBalanceSummary(clientId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build())
        .onErrorResume(
            ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
  }
}
