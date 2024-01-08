package org.neomind.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.neomind.Entity.Fornecedor;
import org.neomind.Repository.FornecedorRepository;

import java.util.List;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {
    @Inject
    private FornecedorRepository fornecedorRepository;

    @Override
    public List<Fornecedor> getAllFornecedoresPaginados(int startIndex, int endIndex) {
        return fornecedorRepository.findAllPaginados(startIndex, endIndex);
    }

    @Override
    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Fornecedor getFornecedorById(Long id) {
        return fornecedorRepository.findUniqueFornecedorById(id);
    }

    @Override
    public void deleteFornecedor(Long id) {
        fornecedorRepository.deleteUniqueFornecedor(id);
    }

    @Override
    public Fornecedor editFornecedor(Long id, Fornecedor fornecedor) throws Exception {
        return fornecedorRepository.updateFornecedor(id, fornecedor);

    }

    @Override
    public void createFornecedor(Fornecedor fornecedor) throws Exception {
        fornecedorRepository.create(fornecedor);


    }

    @Override
    public boolean validateCnpj(String cnpj) {
        // Remoção da máscara
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Calcula e verifica os dois últimos dígitos (dígitos verificadores)
        int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicadores1[i];
        }

        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicadores2[i];
        }

        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;

        return (cnpj.charAt(12) - '0' == digitoVerificador1) && (cnpj.charAt(13) - '0' == digitoVerificador2);
    }


    @Override
    public boolean validateEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        return true;
    }


}
