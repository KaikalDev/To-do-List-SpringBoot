package kaique.luan.dev.service.generic;

import kaique.luan.dev.domain.Persistente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface IGenericServices<T extends Persistente> {

    public T cadastrar(T entity);

    public void excluir(T entity);

    public T alterar(T entity);

    public T consultar(Long valor);

    public Collection<T> buscarTodos();
}
