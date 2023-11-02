package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "accounts")
public class AccountEntity {
    @Id
    private String id;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private Integer maxMonthlyTransactions;
    private Double maintenanceFee;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date modifyDate;
    private String ownerId;
    private Double minimumOpeningAmount;
    @Field(targetType = FieldType.IMPLICIT)
    private List<String>  authorizedSigners;
    @Field(targetType = FieldType.IMPLICIT)
    private String bankId;
}
