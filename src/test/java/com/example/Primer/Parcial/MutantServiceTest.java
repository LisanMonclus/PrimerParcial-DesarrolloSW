package com.example.Primer.Parcial;

import com.example.Primer.Parcial.DnaEntity;
import com.example.Primer.Parcial.repository.DnaRepository;
import com.example.Primer.Parcial.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MutantServiceTest {

    @Autowired
    private MutantService mutantService;

    @Autowired
    private DnaRepository dnaRepository;

    @Test
    public void testIsMutantWhenExists() {
        // Preparar los datos
        String dnaSequence = "ATGCGA";
        DnaEntity dnaEntity = new DnaEntity(dnaSequence, true);
        dnaRepository.save(dnaEntity);

        // Llamar al método
        boolean result = mutantService.isMutant(new String[]{"ATGCGA"});

        // Verificar el resultado
        assertTrue(result);
    }

    @Test
    public void testIsMutantWhenDoesNotExist() {
        String dnaSequence = "CAGTGC";

        // Llamar al método
        boolean result = mutantService.isMutant(new String[]{"CAGTGC"});

        // Verificar el resultado
        assertFalse(result);
    }
}
