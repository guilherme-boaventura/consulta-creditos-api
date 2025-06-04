package glhrme.bvt.consulta_credito.service;

import glhrme.bvt.consulta_credito.model.Credito;
import glhrme.bvt.consulta_credito.repository.CreditoRepository;
import glhrme.bvt.consulta_credito.utils.RegistroNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CreditoService extends ServiceBase<Credito, CreditoRepository> {

    protected CreditoService(CreditoRepository repo) {
        super(repo);
    }

    @Transactional(readOnly = true)
    public List<Credito> findByNumeroNfse(String numeroNfse) {

        if(!StringUtils.hasText(numeroNfse)) {
            throw new IllegalArgumentException("Um número de nota fiscal eletrônica deve ser informado para consulta.");
        }

        return repo.findByNumeroNfse(numeroNfse);
    }

    @Transactional(readOnly = true)
    public Credito findByNumeroCredito(String numeroCredito) {

        if(!StringUtils.hasText(numeroCredito)) {
            throw new IllegalArgumentException("Um número de crédito deve ser informado para consulta.");
        }

        return repo.findByNumeroCredito(numeroCredito)
                            .orElseThrow(()-> new RegistroNaoEncontradoException("Não há registro de crédito com o número informado."));
    }
}