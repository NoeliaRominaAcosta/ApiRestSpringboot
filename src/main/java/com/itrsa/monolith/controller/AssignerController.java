package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.AssignerDTO;
import com.itrsa.monolith.service.AssignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assigner")
public class AssignerController {
    @Autowired
    private AssignerService serviceAssigner;

    @GetMapping("/get/{dni}")
    public AssignerDTO getAssigner(@PathVariable String dni) {
        return  serviceAssigner.getAssigner(dni);
    }

    @PutMapping("/edit")
    public void editAssigner(@RequestBody AssignerDTO assigner) {
        serviceAssigner.editAssigner(assigner);
    }
    @DeleteMapping("/delete/{dni}")
    public void deleteAssigner(@PathVariable String dni) {
        serviceAssigner.deleteAssigner(dni);
    }
    @PostMapping("/create")
    public void addAssigner(@RequestBody AssignerDTO assigner) {
        serviceAssigner.addAssigner(assigner);
    }
}
