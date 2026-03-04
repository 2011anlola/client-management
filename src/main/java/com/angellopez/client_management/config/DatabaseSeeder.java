package com.angellopez.client_management.config;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) {
        if (clientRepository.count() == 0) {
            seedClients();
        }
    }

    private void seedClients() {
        clientRepository.save(Client.builder().name("Alice Johnson").email("alice@example.com").phone("+1-555-1010").address("123 Maple Street").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Bob Smith").email("bob@example.com").phone("+1-555-1020").address("456 Oak Avenue").country("Canada").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Charlie Brown").email("charlie@example.com").phone("+44-20-1030").address("789 Pine Road").country("UK").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Diana Prince").email("diana@example.com").phone("+1-555-1040").address("Themyscira Island").country("Greece").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Edward Norton").email("edward@example.com").phone("+1-555-1050").address("12 Elm Street").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Fiona Gallagher").email("fiona@example.com").phone("+1-555-1060").address("34 Cedar Avenue").country("Canada").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("George Clooney").email("george@example.com").phone("+44-20-1070").address("56 Birch Lane").country("UK").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Hannah Montana").email("hannah@example.com").phone("+1-555-1080").address("78 Spruce Road").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Ian McKellen").email("ian@example.com").phone("+44-20-1090").address("90 Willow Street").country("UK").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Jessica Alba").email("jessica@example.com").phone("+1-555-1100").address("101 Oak Street").country("Canada").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Kevin Hart").email("kevin@example.com").phone("+1-555-1110").address("202 Pine Avenue").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Laura Palmer").email("laura@example.com").phone("+44-20-1120").address("303 Maple Lane").country("UK").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Michael Jordan").email("michael@example.com").phone("+1-555-1130").address("404 Cedar Road").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Natalie Portman").email("natalie@example.com").phone("+44-20-1140").address("505 Birch Street").country("UK").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Oscar Isaac").email("oscar@example.com").phone("+1-555-1150").address("606 Elm Lane").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Penelope Cruz").email("penelope@example.com").phone("+44-20-1160").address("707 Spruce Avenue").country("Spain").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Quentin Tarantino").email("quentin@example.com").phone("+1-555-1170").address("808 Willow Road").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Rachel McAdams").email("rachel@example.com").phone("+44-20-1180").address("909 Oak Lane").country("Canada").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Samuel Jackson").email("samuel@example.com").phone("+1-555-1190").address("1010 Maple Street").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Tina Fey").email("tina@example.com").phone("+44-20-1200").address("1111 Pine Road").country("UK").status(ClientStatus.ACTIVE).build());
    }
}