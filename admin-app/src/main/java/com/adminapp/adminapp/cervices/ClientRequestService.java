package com.adminapp.adminapp.cervices;

import com.adminapp.adminapp.dto.ClientDto;

import java.util.List;

public interface ClientRequestService {
    List<ClientDto> getAllClients();
    ClientDto getClient(int id);
    void saveOrUpdateClient(ClientDto client);
    void deleteClient(int id);
}
