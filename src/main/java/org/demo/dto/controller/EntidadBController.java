package org.demo.dto.controller;

import org.demo.dto.domain.EntidadA;
import org.demo.dto.domain.EntidadB;
import org.demo.dto.repository.EntidadARepository;
import org.demo.dto.repository.EntidadBRepository;
import org.demo.dto.repository.EntidadCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entidadesB")
public class EntidadBController {
    @Autowired
    private EntidadBRepository repositoryB;

    @Autowired
    private EntidadARepository repositoryA;


    @PostMapping
    public EntidadB create(@RequestBody EntidadB entidadB) {
        return repositoryB.save(entidadB);
    }

    @GetMapping
    public List<EntidadB> getAll() {
        return repositoryB.findAll();
    }

    @PostMapping("/{entidadBId}/entidadA/{entidadAId}")
    public ResponseEntity<?> addEntidadAToEntidadB(@PathVariable Long entidadBId, @PathVariable Long entidadAId) {
        Optional<EntidadB> optionalEntidadB = repositoryB.findById(entidadBId);
        Optional<EntidadA> optionalEntidadA = repositoryA.findById(entidadAId);

        if (optionalEntidadB.isPresent() && optionalEntidadA.isPresent()) {
            EntidadB entidadB = optionalEntidadB.get();
            EntidadA entidadA = optionalEntidadA.get();
//            entidadB.setEntidadA(entidadA);``
            repositoryB.save(entidadB);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
