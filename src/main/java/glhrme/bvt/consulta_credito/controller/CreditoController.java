package glhrme.bvt.consulta_credito.controller;

import glhrme.bvt.consulta_credito.model.Credito;
import glhrme.bvt.consulta_credito.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping("/{numeroNfse}")
    public List<Credito> obterPorNumeroNfse(@PathVariable("numeroNfse") String numeroNfse) {
        return creditoService.findByNumeroNfse(numeroNfse);
    }

    @GetMapping("/credito/{numeroCredito}")
    public Credito obterPorNumeroCredito(@PathVariable("numeroCredito") String numeroCredito) {
        return creditoService.findByNumeroCredito(numeroCredito);
    }

}
