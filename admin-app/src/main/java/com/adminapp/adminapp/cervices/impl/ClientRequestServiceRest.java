package com.adminapp.adminapp.cervices.impl;

import com.adminapp.adminapp.cervices.ClientRequestService;
import com.adminapp.adminapp.dto.ClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientRequestServiceRest implements ClientRequestService {

    @Value("${client-crud-app.host}")
    private String host;

    @Override
    public List<ClientDto> getAllClients() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.exchange(host + "/client/find-all", HttpMethod.GET, HttpEntity.EMPTY, List.class);
        List<ClientDto> clients = responseEntity.getBody();
        return clients;
    }

    @Override
    public ClientDto getClient(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDto> responseEntity = restTemplate.exchange(host + "/client/" + id, HttpMethod.GET, HttpEntity.EMPTY, ClientDto.class);
        ClientDto client = responseEntity.getBody();
        return client;
    }

    @Override
    public void saveOrUpdateClient(ClientDto client) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(client, headers);

        restTemplate.exchange(host + "/client", HttpMethod.POST, entity, ClientDto.class);
    }

    @Override
    public void deleteClient(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(host + "/client/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, ClientDto.class);
    }

}
