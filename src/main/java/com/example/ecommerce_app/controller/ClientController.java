package com.example.ecommerce_app.controller;

import com.example.ecommerce_app.entity.Client;
import com.example.ecommerce_app.entity.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Import this
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerce_app.repository.ClientRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        // Check if a client with the same email exists
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("A client with this email already exists.");
        }
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Client> clientOpt = clientRepository.findByEmail(loginRequest.getEmail());

        if (!clientOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        Client client = clientOpt.get();

        // Verify hashed password (assuming you implement hashing)
        // if (!passwordEncoder.matches(loginRequest.getPassword(), client.getPassword())) {
        if (!client.getPassword().equals(loginRequest.getPassword())) { // Placeholder for hashing
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Placeholder for JWT token
        String token = "sampleToken"; // Replace with actual JWT token generation logic

        return ResponseEntity.ok(
                Map.of(
                        "id", client.getId(),
                        "email", client.getEmail(),
                        "token", token
                )
        );
    }

    @GetMapping
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setEmail(clientDetails.getEmail());
        client.setPassword(clientDetails.getPassword());
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        clientRepository.delete(client);
        return ResponseEntity.ok("Client deleted successfully.");
    }
}
