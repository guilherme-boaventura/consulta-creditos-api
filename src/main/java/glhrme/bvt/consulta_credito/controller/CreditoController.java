package glhrme.bvt.consulta_credito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import glhrme.bvt.consulta_credito.model.Credito;
import glhrme.bvt.consulta_credito.service.CreditoService;
import glhrme.bvt.consulta_credito.utils.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private KafkaProducerService kafkaProducer;

    @Autowired
    private CreditoService creditoService;

    @GetMapping("/{numeroNfse}")
    public List<Credito> obterPorNumeroNfse(@PathVariable("numeroNfse") String numeroNfse) throws JsonProcessingException {
        List<Credito> creditos = creditoService.findByNumeroNfse(numeroNfse);

        kafkaProducer.sendMessage("consulta_credito", "obterPorNumeroNfse", Map.of("numeroConsultado", numeroNfse, "retornoConsulta", creditos));
        return creditos;
    }

    @GetMapping("/credito/{numeroCredito}")
    public Credito obterPorNumeroCredito(@PathVariable("numeroCredito") String numeroCredito) throws JsonProcessingException {
        Credito credito = creditoService.findByNumeroCredito(numeroCredito);

        kafkaProducer.sendMessage("consulta_credito", "obterPorNumeroCredito", Map.of("numeroConsultado", numeroCredito, "retornoConsulta", credito));
        return credito;
    }
}