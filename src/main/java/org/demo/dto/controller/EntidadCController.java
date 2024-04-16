package org.demo.dto.controller;

import org.demo.dto.domain.EntidadB;
import org.demo.dto.domain.EntidadC;
import org.demo.dto.repository.EntidadARepository;
import org.demo.dto.repository.EntidadBRepository;
import org.demo.dto.repository.EntidadCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entidadesC")
public class EntidadCController {
    @Autowired
    private EntidadCRepository repositoryC;
    @Autowired
    private EntidadARepository repositoryA;
    @Autowired
    private EntidadBRepository repositoryB;

    @PostMapping
    public EntidadC create(@RequestBody EntidadC entidadC) {
        return repositoryC.save(entidadC);
    }

    @GetMapping
    public List<EntidadC> getAll() {
        return repositoryC.findAll();
    }

    @PostMapping("/{entidadCId}/entidadB/{entidadBId}")
    public ResponseEntity<?> addEntidadBToEntidadC(@PathVariable Long entidadCId, @PathVariable Long entidadBId) {
        Optional<EntidadC> optionalEntidadC = repositoryC.findById(entidadCId);
        Optional<EntidadB> optionalEntidadB = repositoryB.findById(entidadBId);

        if (optionalEntidadC.isPresent() && optionalEntidadB.isPresent()) {
            EntidadC entidadC = optionalEntidadC.get();
            EntidadB entidadB = optionalEntidadB.get();
            entidadC.setEntidadB(entidadB);
            repositoryC.save(entidadC);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}