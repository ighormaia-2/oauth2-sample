package com.oauth2.service;

import com.oauth2.exception.ResourceNotFoundException;
import com.oauth2.model.Client;
import com.oauth2.model.ClientDto;
import com.oauth2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private static final String CLIENT_NOT_FOUND = "Client not found: id ";
    private static final String CLIENT_DELETED = "Client deleted: ";

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long clientId) throws ResourceNotFoundException {
        return clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND + clientId));
    }

    public Client createClient(ClientDto clientDto) {
        return clientRepository.save(clientDto.convertToClient());
    }

    public Client updateClient(Long clientId, ClientDto clientDto) throws ResourceNotFoundException {
        try {
            Client client = this.clientRepository.getOne(clientId);

            client.setEmail(clientDto.getEmail());
            client.setName(clientDto.getName());
            client.setPhone(clientDto.getPhone());

            return clientRepository.save(client);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(CLIENT_NOT_FOUND + clientId, e);
        }
    }

    public Map<String, Boolean> deleteClient(Long clientId) throws ResourceNotFoundException {
        try {
            Client client = this.clientRepository.getOne(clientId);

            clientRepository.delete(client);
            Map<String, Boolean> response = new HashMap<>();
            response.put(CLIENT_DELETED, Boolean.TRUE);

            return response;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(CLIENT_NOT_FOUND + clientId, e);
        }
    }
}
