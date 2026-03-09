package com.angellopez.client_management.config;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Database seeder component that populates the database with sample client data on application startup.
 */
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ClientRepository clientRepository;

    /**
     * Runs the seeder if the database is empty.
     * @param args command line arguments
     */
    @Override
    public void run(String... args) {
        if (clientRepository.count() == 0) {
            seedClients();
        }
    }

    /**
     * Seeds the database with sample client data.
     */
    private void seedClients() {
        /*clientRepository.save(Client.builder().name("Alice Johnson").email("alice@example.com").phone("+1-555-1010").address("123 Maple Street").country("USA").status(ClientStatus.ACTIVE).build());
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
*/
        clientRepository.save(Client.builder().name("Carlos Mendoza").email("carlos.mendoza@example.com").phone("+34-555-1101").address("45 Calle Mayor").country("Spain").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Sophie Laurent").email("sophie.laurent@example.com").phone("+33-555-1102").address("12 Rue Victor Hugo").country("France").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Liam O'Connor").email("liam.oconnor@example.com").phone("+353-555-1103").address("78 Abbey Road").country("Ireland").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Hiroshi Tanaka").email("hiroshi.tanaka@example.com").phone("+81-555-1104").address("3-12 Shibuya").country("Japan").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Mateo Silva").email("mateo.silva@example.com").phone("+55-555-1105").address("210 Avenida Paulista").country("Brazil").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Anna Kowalska").email("anna.kowalska@example.com").phone("+48-555-1106").address("16 Krakowska Street").country("Poland").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Noah Muller").email("noah.muller@example.com").phone("+49-555-1107").address("5 Berliner Platz").country("Germany").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Olivia Rossi").email("olivia.rossi@example.com").phone("+39-555-1108").address("22 Via Roma").country("Italy").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Ethan Smith").email("ethan.smith@example.com").phone("+1-555-1109").address("455 Oak Avenue").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Isabella Fernandez").email("isabella.fernandez@example.com").phone("+54-555-1110").address("88 Avenida Santa Fe").country("Argentina").status(ClientStatus.ACTIVE).build());

        clientRepository.save(Client.builder().name("Lucas Pereira").email("lucas.pereira@example.com").phone("+351-555-1111").address("10 Rua Augusta").country("Portugal").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Mila Petrova").email("mila.petrova@example.com").phone("+359-555-1112").address("34 Vitosha Blvd").country("Bulgaria").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Daniel Novak").email("daniel.novak@example.com").phone("+420-555-1113").address("90 Wenceslas Square").country("Czech Republic").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Ahmed Hassan").email("ahmed.hassan@example.com").phone("+20-555-1114").address("77 Nile Street").country("Egypt").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Fatima Al-Zahra").email("fatima.alzahra@example.com").phone("+971-555-1115").address("120 Palm Avenue").country("UAE").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Raj Patel").email("raj.patel@example.com").phone("+91-555-1116").address("54 MG Road").country("India").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Wei Zhang").email("wei.zhang@example.com").phone("+86-555-1117").address("6 Nanjing Road").country("China").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Min Soo Kim").email("minsoo.kim@example.com").phone("+82-555-1118").address("11 Gangnam-daero").country("South Korea").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Nguyen Tran").email("nguyen.tran@example.com").phone("+84-555-1119").address("14 Le Loi Street").country("Vietnam").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Samuel Johnson").email("samuel.johnson@example.com").phone("+44-555-1120").address("2 Baker Street").country("United Kingdom").status(ClientStatus.ACTIVE).build());

        clientRepository.save(Client.builder().name("Chloe Martin").email("chloe.martin@example.com").phone("+33-555-1121").address("44 Rue Lafayette").country("France").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Oscar Svensson").email("oscar.svensson@example.com").phone("+46-555-1122").address("19 Storgatan").country("Sweden").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Emma Hansen").email("emma.hansen@example.com").phone("+45-555-1123").address("72 Nyhavn Street").country("Denmark").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Jasper de Vries").email("jasper.devries@example.com").phone("+31-555-1124").address("9 Dam Square").country("Netherlands").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Luca Bianchi").email("luca.bianchi@example.com").phone("+39-555-1125").address("81 Via Milano").country("Italy").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Petar Ivanov").email("petar.ivanov@example.com").phone("+359-555-1126").address("8 Tsar Osvoboditel Blvd").country("Bulgaria").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("George Papadopoulos").email("george.papadopoulos@example.com").phone("+30-555-1127").address("33 Athens Street").country("Greece").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Yusuf Demir").email("yusuf.demir@example.com").phone("+90-555-1128").address("60 Istiklal Avenue").country("Turkey").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Thabo Nkosi").email("thabo.nkosi@example.com").phone("+27-555-1129").address("14 Mandela Drive").country("South Africa").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Diego Alvarez").email("diego.alvarez@example.com").phone("+56-555-1130").address("120 Avenida Libertador").country("Chile").status(ClientStatus.ACTIVE).build());

        clientRepository.save(Client.builder().name("Victor Ramirez").email("victor.ramirez@example.com").phone("+34-555-1131").address("88 Calle del Sol").country("Spain").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Emma Wilson").email("emma.wilson@example.com").phone("+1-555-1132").address("12 Cherry Lane").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Lara Moreau").email("lara.moreau@example.com").phone("+33-555-1133").address("23 Rue de Lyon").country("France").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Marco Conti").email("marco.conti@example.com").phone("+39-555-1134").address("44 Via Torino").country("Italy").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Julia Schmidt").email("julia.schmidt@example.com").phone("+49-555-1135").address("17 Berliner Strasse").country("Germany").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Bruno Costa").email("bruno.costa@example.com").phone("+55-555-1136").address("77 Rua das Flores").country("Brazil").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Sofia Alvarez").email("sofia.alvarez@example.com").phone("+54-555-1137").address("30 Calle Libertad").country("Argentina").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Mateusz Zielinski").email("mateusz.zielinski@example.com").phone("+48-555-1138").address("9 Warszawska Street").country("Poland").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Takashi Suzuki").email("takashi.suzuki@example.com").phone("+81-555-1139").address("18 Shinjuku").country("Japan").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Isabella Rossi").email("isabella.rossi2@example.com").phone("+39-555-1140").address("101 Via Venezia").country("Italy").status(ClientStatus.ACTIVE).build());

        clientRepository.save(Client.builder().name("John Doe").email("john.doe2@example.com").phone("+1-555-1141").address("55 Oak Street").country("USA").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Pierre Dupont").email("pierre.dupont2@example.com").phone("+33-555-1142").address("8 Rue de Paris").country("France").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Andrea Bianchi").email("andrea.bianchi@example.com").phone("+39-555-1143").address("13 Via Milano").country("Italy").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Anna Müller").email("anna.mueller@example.com").phone("+49-555-1144").address("22 Hauptstrasse").country("Germany").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Lucas Silva").email("lucas.silva2@example.com").phone("+55-555-1145").address("14 Rua Central").country("Brazil").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Maria Gomez").email("maria.gomez@example.com").phone("+54-555-1146").address("66 Calle Flores").country("Argentina").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Pedro Fernandez").email("pedro.fernandez@example.com").phone("+34-555-1147").address("90 Calle Luna").country("Spain").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Hiro Tanaka").email("hiro.tanaka@example.com").phone("+81-555-1148").address("7 Shibuya Street").country("Japan").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Olivia Smith").email("olivia.smith2@example.com").phone("+1-555-1149").address("120 Pine Avenue").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Sophie Laurent").email("sophie.laurent2@example.com").phone("+33-555-1150").address("31 Rue Lafayette").country("France").status(ClientStatus.ACTIVE).build());

        clientRepository.save(Client.builder().name("Giuseppe Romano").email("giuseppe.romano@example.com").phone("+39-555-1151").address("55 Via Napoli").country("Italy").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Friedrich Weber").email("friedrich.weber@example.com").phone("+49-555-1152").address("19 Schillerstrasse").country("Germany").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Carla Pereira").email("carla.pereira@example.com").phone("+55-555-1153").address("21 Rua Nova").country("Brazil").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Lucia Fernandez").email("lucia.fernandez@example.com").phone("+54-555-1154").address("47 Avenida Belgrano").country("Argentina").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Miguel Torres").email("miguel.torres@example.com").phone("+34-555-1155").address("88 Calle Sol").country("Spain").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Yuki Nakamura").email("yuki.nakamura@example.com").phone("+81-555-1156").address("12 Shinjuku Road").country("Japan").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Ethan Brown").email("ethan.brown2@example.com").phone("+1-555-1157").address("99 Maple Street").country("USA").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Camille Dubois").email("camille.dubois@example.com").phone("+33-555-1158").address("20 Rue Victor Hugo").country("France").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Antonio Ricci").email("antonio.ricci2@example.com").phone("+39-555-1159").address("14 Via Torino").country("Italy").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Hans Becker").email("hans.becker@example.com").phone("+49-555-1160").address("33 Berliner Strasse").country("Germany").status(ClientStatus.INACTIVE).build());

        clientRepository.save(Client.builder().name("Santiago Lopez").email("santiago.lopez@example.com").phone("+34-555-1161").address("55 Calle Mayor").country("Spain").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Lucas Santos").email("lucas.santos@example.com").phone("+55-555-1162").address("77 Rua Central").country("Brazil").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Isabella Martinez").email("isabella.martinez@example.com").phone("+54-555-1163").address("42 Calle Libertad").country("Argentina").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Haruto Yamamoto").email("haruto.yamamoto@example.com").phone("+81-555-1164").address("88 Shibuya Road").country("Japan").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Mia Johnson").email("mia.johnson2@example.com").phone("+1-555-1165").address("65 Cedar Avenue").country("USA").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Chloe Martin").email("chloe.martin2@example.com").phone("+33-555-1166").address("19 Rue de Rennes").country("France").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Gabriel Fernandez").email("gabriel.fernandez@example.com").phone("+54-555-1167").address("18 Calle San Martin").country("Argentina").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Elena Rossi").email("elena.rossi@example.com").phone("+39-555-1168").address("7 Via Firenze").country("Italy").status(ClientStatus.ACTIVE).build());
        clientRepository.save(Client.builder().name("Noah Weber").email("noah.weber@example.com").phone("+49-555-1169").address("22 Bremer Strasse").country("Germany").status(ClientStatus.INACTIVE).build());
        clientRepository.save(Client.builder().name("Charlotte Martin").email("charlotte.martin@example.com").phone("+33-555-1171").address("44 Rue Saint-Honoré").country("France").status(ClientStatus.ACTIVE).build());
    }
}