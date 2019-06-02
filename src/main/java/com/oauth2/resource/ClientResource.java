package com.oauth2.resource;

import com.oauth2.exception.ResourceNotFoundException;
import com.oauth2.model.Client;
import com.oauth2.model.ClientDto;
import com.oauth2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClientResource {

    private final ClientService clientService;

    @Autowired
    private ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(name = "id") Long clientId) {
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDto clientDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(clientService.createClient(clientDto));
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(name = "id") Long clientId, @Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateClient(clientId, clientDto));
    }

    @DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(name = "id") Long clientId) {
        return clientService.deleteClient(clientId);
    }
}
