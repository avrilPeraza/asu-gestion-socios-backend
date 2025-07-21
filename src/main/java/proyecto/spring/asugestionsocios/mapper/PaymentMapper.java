package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.PaymentDTO.MonthlyFeeDTO;
import proyecto.spring.asugestionsocios.model.dto.PaymentDTO.PaymentCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.PaymentDTO.PaymentDTO;
import proyecto.spring.asugestionsocios.model.entity.MonthlyFee;
import proyecto.spring.asugestionsocios.model.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toEntity(PaymentDTO paymentDTO);
    PaymentDTO toDto(Payment payment);
    Payment toEntityCreate(PaymentCreateDTO paymentCreateDTO);
    MonthlyFee toEntityMonthlyFee(MonthlyFeeDTO monthlyFeeDTO);
}
