package nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.services;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.usercases.InquiryReportService;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AverageBalanceSummaryResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.AccountServicePort;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.CreditServicePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class InquiryReportManagementServices implements InquiryReportService {

  private final CreditServicePort creditServicePort;
  private final AccountServicePort  accountServicePort;

  @Override
  public Mono<AverageBalanceSummaryResponse> generateAverageBalanceSummary(String clientId) {
    return null;
  }
}
