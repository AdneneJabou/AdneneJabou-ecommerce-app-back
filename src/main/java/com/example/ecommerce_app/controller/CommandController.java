package com.example.ecommerce_app.controller;

import com.example.ecommerce_app.entity.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerce_app.repository.CommandRepository;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    @Autowired
    private CommandRepository commandRepository;

    // 1. Create a new Command
    @PostMapping
    public ResponseEntity<?> createCommand(@RequestBody Command command) {
        try {
            Command savedCommand = commandRepository.save(command);
            return ResponseEntity.status(201).body(savedCommand);
        } catch (Exception e) {
            // Log the exception for debugging
            System.err.println("Error saving command: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to place order. Please try again.");
        }
    }


    // 2. Get all Commands
    @GetMapping
    public List<Command> getAllCommands() {
        return commandRepository.findAll();
    }

    // 3. Get Command by ID
    @GetMapping("/{id}")
    public ResponseEntity<Command> getCommandById(@PathVariable Long id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found with id: " + id));
        return ResponseEntity.ok(command);
    }

    // 4. Update a Command
    @PutMapping("/{id}")
    public ResponseEntity<Command> updateCommand(@PathVariable Long id, @RequestBody Command commandDetails) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found with id: " + id));

        command.setIdClient(commandDetails.getIdClient());
        command.setIdProduct(commandDetails.getIdProduct());
        command.setDate(commandDetails.getDate());

        Command updatedCommand = (Command) commandRepository.save(command);
        return ResponseEntity.ok(updatedCommand);
    }

    // 5. Delete a Command
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommand(@PathVariable Long id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found with id: " + id));
        commandRepository.delete(command);
        return ResponseEntity.ok("Command deleted successfully.");
    }
}
