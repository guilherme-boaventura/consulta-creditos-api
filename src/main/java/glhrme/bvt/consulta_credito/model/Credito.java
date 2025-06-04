package glhrme.bvt.consulta_credito.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Credito extends ModelBase {

    private String numeroCredito;
    private String numeroNfse;
    private LocalDate dataConstituicao;
    private BigDecimal valorIssqn;
    private String tipoCredito;
    private boolean simplesNacional;
    private BigDecimal aliquota;
    private BigDecimal valorFaturado;
    private BigDecimal valorDeducao;
    private BigDecimal baseCalculo;

}