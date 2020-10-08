package service;

import entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void save (Client client){
        clientRepository.save(client);
    }

    public List<Client> getAll(){
        return (List<Client>) clientRepository.findAll();
    }

    public Optional<Client> getById(int id){
        return clientRepository.findById(id);
    }

    public void remove (Client client){
        clientRepository.delete(client);
    }

}
