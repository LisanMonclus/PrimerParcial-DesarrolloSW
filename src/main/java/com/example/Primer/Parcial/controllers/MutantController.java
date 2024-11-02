package com.example.Primer.Parcial.controllers;

import com.example.Primer.Parcial.DnaSequence;
import com.example.Primer.Parcial.StatsResponse;
import com.example.Primer.Parcial.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaSequence dnaSequence) {
        boolean isMutant = mutantService.isMutant(dnaSequence.getDna());
        return isMutant ? ResponseEntity.ok("Mutant detected")
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("Human detected");
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        StatsResponse stats = mutantService.getStats();
        return ResponseEntity.ok(stats);
    }
}
