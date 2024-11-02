package com.example.Primer.Parcial.services;

import com.example.Primer.Parcial.DnaEntity;
import com.example.Primer.Parcial.StatsResponse;
import com.example.Primer.Parcial.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    @Autowired
    private DnaRepository dnaRepository;

    // Método para verificar si el ADN es mutante y almacenar el resultado
    public boolean isMutant(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verifica si el ADN ya ha sido almacenado
        return dnaRepository.findByDnaSequence(dnaSequence)
                .map(DnaEntity::isMutant)
                .orElseGet(() -> {
                    boolean isMutant = detectMutant(dna);
                    dnaRepository.save(new DnaEntity(dnaSequence, isMutant));
                    return isMutant;
                });
    }

    // Lógica de detección de ADN mutante
    private boolean detectMutant(String[] dna) {
        int n = dna.length;
        int sequencesFound = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (hasSequence(dna, i, j, 0, 1)) sequencesFound++;
                if (hasSequence(dna, j, i, 1, 0)) sequencesFound++;
                if (sequencesFound > 1) return true;
            }
        }

        // Diagonales
        for (int i = 0; i <= n - 4; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (hasSequence(dna, i, j, 1, 1)) sequencesFound++;
                if (hasSequence(dna, i, n - j - 1, 1, -1)) sequencesFound++;
                if (sequencesFound > 1) return true;
            }
        }

        return false;
    }

    // Verifica secuencias de bases iguales en una dirección
    private boolean hasSequence(String[] dna, int row, int col, int rowInc, int colInc) {
        char base = dna[row].charAt(col);
        for (int k = 1; k < 4; k++) {
            if (dna[row + k * rowInc].charAt(col + k * colInc) != base) return false;
        }
        return true;
    }

    // Retorna las estadísticas de verificación de ADN
    public StatsResponse getStats() {
        long countMutant = dnaRepository.countByIsMutantTrue();
        long countHuman = dnaRepository.countByIsMutantFalse();
        double ratio = countHuman > 0 ? (double) countMutant / countHuman : 0.0;

        return new StatsResponse(countMutant, countHuman, ratio);
    }
}
