package glhrme.bvt.consulta_credito.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public class ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}