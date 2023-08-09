package com.oracle.graal.demo.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.graal.demo.model.Country;
import com.oracle.graal.demo.repos.CountryRepository;

/**
 *
 * @author rjay
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/countries")
public class CountryController {
    private final CountryRepository repository;

    @Autowired
    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public Iterable<Country> list() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Country input) {
        try {
            Optional<Country> compare = repository.findById(id);
            if (compare.isPresent() && compare.get().getCode().equals(input.getCode())) {
                repository.save(input);
                return new ResponseEntity<>(input, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Country input) {
        try {
            Optional<Country> compare = repository.findById(input.getCode());
            if (compare.isEmpty()) {
                repository.save(input);
                return new ResponseEntity<>(input, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            Optional<Country> input = repository.findById(id);
            if (input.isPresent()) {
                repository.deleteById(id);
                return new ResponseEntity<>(input, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
