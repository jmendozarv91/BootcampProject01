package nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.usercases;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AverageBalanceSummaryResponse;
import reactor.core.publisher.Mono;

public interface InquiryReportService {
  Mono<AverageBalanceSummaryResponse> generateAverageBalanceSummary(String clientId);
}
