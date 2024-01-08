package org.neomind.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FornecedorServiceImplTest {

    @Test
    public void testValidateCnpj(){
        FornecedorServiceImpl service = new FornecedorServiceImpl();
        // CNPJ válido com máscara
        assertTrue(service.validateCnpj("12.345.678/0001-00"));

        // CNPJ válido sem máscara
        assertTrue(service.validateCnpj("12345678000100"));

        // CNPJ inválido com menos de 14 dígitos
        assertFalse(service.validateCnpj("1234567890"));

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