package kaique.luan.dev.service.generic;

import kaique.luan.dev.domain.Persistente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public abstract class GenericService<T extends Persistente, R extends JpaRepository<T, Long>>
        implements IGenericServices<T> {

    protected R repository;

    protected GenericService(R repository) {
        this.repository = repository;
    }

    @Override
    public T cadastrar(T entity) {
        return repository.save(entity);
    }

    @Override
    public void excluir(T entity) {
        repository.delete(entity);
    }

    @Override
    public T alterar(T entity) {
        return repository.save(entity);
    }

    @Override
    public T consultar(Long valor) {
        Optional<T> result = repository.findById(valor);
        return result.orElse(null);
    }

    @Override
    public Collection<T> buscarTodos() {
        return repository.findAll();
    }
}
