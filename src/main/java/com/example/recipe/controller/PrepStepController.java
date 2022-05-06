package com.example.recipe.controller;

import com.example.recipe.model.PrepStep;
import com.example.recipe.repository.PrepStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PrepStepController {

    @Autowired
    PrepStepRepository repo;

    @PutMapping("/prepsteps")
    public void updatePrepStep(@RequestBody PrepStep prepStep) { repo.save(prepStep); }

    @PostMapping("/prepsteps")
    @ResponseStatus(HttpStatus.CREATED)
    public PrepStep addPrepStep(@RequestBody PrepStep prepStep) { return repo.save(prepStep); }

    @GetMapping("/prepsteps/recipe/{id}")
    public List<PrepStep> getPrepStepsByRecipe(@PathVariable Integer id) {
        return repo.findAllPrepStepsByRecipeId(id);
    }

    @GetMapping("/prepsteps/{id}")
    public PrepStep getPrepStep(@PathVariable Integer id) {
        Optional<PrepStep> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/prepsteps/{id}")
    public void deletePrepStep(@PathVariable Integer id) { repo.deleteById(id); }
}
