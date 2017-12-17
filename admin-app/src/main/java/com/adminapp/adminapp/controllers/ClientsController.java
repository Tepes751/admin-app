package com.adminapp.adminapp.controllers;

import com.adminapp.adminapp.cervices.ClientRequestService;
import com.adminapp.adminapp.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ClientsController {

    @Autowired
    private ClientRequestService clientRequestService;

    @RequestMapping("/")
    public String clientsList(Model model){
        List<ClientDto> clients = clientRequestService.getAllClients();

        model.addAttribute("clients", clients);

        return "clients/clients";
    }

    @RequestMapping("/client/{id}")
    public String clientDetails(@PathVariable int id, Model model){
        ClientDto clientDto = clientRequestService.getClient(id);

        model.addAttribute("client", clientDto);

        return "clients/client-details";
    }

    @RequestMapping("/client/edit/{id}")
    public String clientEditForm(@PathVariable int id, Model model){
        ClientDto clientDto = clientRequestService.getClient(id);

        model.addAttribute("client", clientDto);

        return "clients/client-form";
    }

    @RequestMapping("/client/new")
    public String clientCreateNewForm(Model model){
        ClientDto clientDto = new ClientDto();

        model.addAttribute("client", clientDto);
        return "clients/client-form";
    }

    @RequestMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable int id){
        clientRequestService.deleteClient(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/client/save-or-update", method = RequestMethod.POST)
    public String saveOrUpdate(ClientDto clientDto){
        clientRequestService.saveOrUpdateClient(clientDto);

        return "redirect:/";
    }
}
