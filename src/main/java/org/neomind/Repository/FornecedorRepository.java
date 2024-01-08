package org.neomind.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.neomind.Entity.Fornecedor;

import java.util.List;

@ApplicationScoped
public class FornecedorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Fornecedor> findAllPaginados(int startIndex, int endIndex) {
        int maxResults = endIndex - startIndex;
        TypedQuery<Fornecedor> query = entityManager.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class);
        query.setFirstResult(startIndex);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    public List<Fornecedor> findAll() {
        TypedQuery<Fornecedor> query = entityManager.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class);
        return query.getResultList();
    }

    public Fornecedor findUniqueFornecedorById(Long id) {
        return entityManager.find(Fornecedor.class, id);
    }

    @Transactional
    public void create(Fornecedor fornecedor) {
        entityManager.persist(fornecedor);
    }

    @Transactional
    public Fornecedor updateFornecedor(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorExistente = entityManager.find(Fornecedor.class, id);
        if (fornecedorExistente != null) {
            fornecedorExistente.setName(fornecedor.getName());
            fornecedorExistente.setEmail(fornecedor.getEmail());
            fornecedorExistente.setDescription(fornecedor.getDescription());
            fornecedorExistente.setCnpj(fornecedor.getCnpj());
            entityManager.merge(fornecedorExistente);
            return fornecedorExistente;
        } else {
            throw new EntityNotFoundException("Fornecedor não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deleteUniqueFornecedor(Long id) {
        Fornecedor fornecedor = entityManager.find(Fornecedor.class, id);
        if (fornecedor != null) {
            entityManager.remove(fornecedor);
        } else {
            throw new EntityNotFoundException("Fornecedor não encontrado com o ID: " + id);
        }
    }
}
