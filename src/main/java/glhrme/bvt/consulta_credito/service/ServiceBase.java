package glhrme.bvt.consulta_credito.service;

import glhrme.bvt.consulta_credito.model.ModelBase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ServiceBase<T extends ModelBase, R extends JpaRepository<T, Long>> {

    protected R repo;

    protected ServiceBase (R repo) {
        this.repo = repo;
    }

    @Transactional(rollbackFor = Exception.class)
    public T salvar(T model) throws InvalidAttributesException {
        validar(model);
        validarUnicidade(model);
        return repo.save(model);
    }

    public void validar(T model) throws InvalidAttributesException {

    }

    public void validarUnicidade(T model) throws DataIntegrityViolationException {

    }

    public void validarExclusao(T model) throws DataIntegrityViolationException {

    }

    public T obterPorId(Long id, Class<T> classe) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe registro de " + classe.getSimpleName() +  " com o id informado."));
    }

    public List<T> obterTodos() {
        return repo.findAll();
    }

    public void excluir(T model) {
        validarExclusao(model);
        repo.delete(model);
    }
}