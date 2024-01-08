package org.neomind.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FornecedorServiceImplTest {

    @Test
    public void testValidateCnpj(){
        FornecedorServiceImpl service = new FornecedorServiceImpl();
        // CNPJ válido com máscara e de acordo com a Receita Federal
        assertTrue(service.validateCnpj("64.798.126/0001-36"));

        // CNPJ válido sem máscara e de acordo com a Receita Federal
        assertTrue(service.validateCnpj("64798126000136"));

        // CNPJ inválido de acordo com a Receita Federal
        assertFalse(service.validateCnpj("64.798.126/0001-99"));

        // CNPJ inválido com menos de 14 dígitos
        assertFalse(service.validateCnpj("64798126000133"));

        // CNPJ inválido com caracteres não numéricos
        assertFalse(service.validateCnpj("12345abc678901"));

    }

    @Test
    public void testValidateEmail(){
        FornecedorServiceImpl service = new FornecedorServiceImpl();
        // Email válido com @ e .
        assertTrue(service.validateEmail("email@example.com"));

        // Email inválido com @ e sem .
        assertFalse(service.validateEmail("teste@gmailcom"));

        // Email inválido com . e sem @
        assertFalse(service.validateEmail("teste.com"));

        // Email inválido sem @ e sem .
        assertFalse(service.validateEmail("invalid_email"));
    }

}