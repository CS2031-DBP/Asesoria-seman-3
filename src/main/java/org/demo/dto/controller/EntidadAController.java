package org.demo.dto.controller;


import org.demo.dto.domain.EntidadA;
import org.demo.dto.domain.EntidadB;
import org.demo.dto.dto.EntidadADto;
import org.demo.dto.repository.EntidadARepository;
import org.demo.dto.repository.EntidadBRepository;
import org.demo.dto.repository.EntidadCRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entidadesA")
public class EntidadAController {
    @Autowired
    private EntidadARepository repositoryA;

    @Autowired
    private EntidadBRepository repositoryB;

    @Autowired
    private EntidadCRepository repositoryC;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public EntidadA create(@RequestBody EntidadA entidadA) {
        return repositoryA.save(entidadA);
    }

    @GetMapping
    public List<EntidadADto> getAll() {

        List<EntidadA> entidad = repositoryA.findAll();

        return entidad
                .stream()
                .map(
                        entidadA -> modelMapper.map(entidadA, EntidadADto.class)
                ).collect(Collectors.toList());

    }


    @PostMapping("/{entidadAId}/entidadB/{entidadBId}")
    public ResponseEntity<?> addEntidadBToEntidadA(@PathVariable Long entidadAId, @PathVariable Long entidadBId) {
        Optional<EntidadA> optionalEntidadA = repositoryA.findById(entidadAId);
        Optional<EntidadB> optionalEntidadB = repositoryB.findById(entidadBId);

        if (optionalEntidadA.isPresent() && optionalEntidadB.isPresent()) {
            EntidadA entidadA = optionalEntidadA.get();
            EntidadB entidadB = optionalEntidadB.get();
            entidadB.setEntidadA(entidadA);
            entidadA.getEntidadBList().add(entidadB);
            repositoryB.save(entidadB);
            repositoryA.save(entidadA);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}