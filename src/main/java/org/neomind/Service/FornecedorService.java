package org.neomind.Service;

import org.neomind.Entity.Fornecedor;

import java.util.List;

public interface FornecedorService {
    List<Fornecedor> getAllFornecedoresPaginados(int startIndex, int endIndex);

    void createFornecedor(Fornecedor fornecedor) throws Exception;

    boolean validateCnpj(String cnpj);

    boolean validateEmail(String email);

    List<Fornecedor> getAllFornecedores();

    Fornecedor getFornecedorById(Long id);

    void deleteFornecedor(Long id);

    Fornecedor editFornecedor(Long id, Fornecedor fornecedor) throws Exception;
}
