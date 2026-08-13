package com.gometro.config;

import com.gometro.entity.Fare;
import com.gometro.entity.MaintenanceIssue;
import com.gometro.entity.MetroCard;
import com.gometro.entity.Payment;
import com.gometro.entity.Route;
import com.gometro.entity.RouteStation;
import com.gometro.entity.Schedule;
import com.gometro.entity.Station;
import com.gometro.entity.Ticket;
import com.gometro.entity.Train;
import com.gometro.entity.User;
import com.gometro.enums.MaintenancePriority;
import com.gometro.enums.MaintenanceStatus;
import com.gometro.enums.MaintenanceType;
import com.gometro.enums.MetroCardStatus;
import com.gometro.enums.PaymentMethod;
import com.gometro.enums.PaymentStatus;
import com.gometro.enums.TicketStatus;
import com.gometro.enums.TrainStatus;
import com.gometro.enums.UserRole;
import com.gometro.repository.FareRepository;
import com.gometro.repository.MaintenanceIssueRepository;
import com.gometro.repository.MetroCardRepository;
import com.gometro.repository.PaymentRepository;
import com.gometro.repository.RouteRepository;
import com.gometro.repository.RouteStationRepository;
import com.gometro.repository.ScheduleRepository;
import com.gometro.repository.StationRepository;
import com.gometro.repository.TicketRepository;
import com.gometro.repository.TrainRepository;
import com.gometro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DemoDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MetroCardRepository metroCardRepository;
    private final StationRepository stationRepository;
    private final RouteRepository routeRepository;
    private final RouteStationRepository routeStationRepository;
    private final TrainRepository trainRepository;
    private final ScheduleRepository scheduleRepository;
    private final FareRepository fareRepository;
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final MaintenanceIssueRepository maintenanceIssueRepository;

    public DemoDataSeeder(
            UserRepository userRepository,
            MetroCardRepository metroCardRepository,
            StationRepository stationRepository,
            RouteRepository routeRepository,
            RouteStationRepository routeStationRepository,
            TrainRepository trainRepository,
            ScheduleRepository scheduleRepository,
            FareRepository fareRepository,
            TicketRepository ticketRepository,
            PaymentRepository paymentRepository,
            MaintenanceIssueRepository maintenanceIssueRepository) {
        this.userRepository = userRepository;
        this.metroCardRepository = metroCardRepository;
        this.stationRepository = stationRepository;
        this.routeRepository = routeRepository;
        this.routeStationRepository = routeStationRepository;
        this.trainRepository = trainRepository;
        this.scheduleRepository = scheduleRepository;
        this.fareRepository = fareRepository;
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
        this.maintenanceIssueRepository = maintenanceIssueRepository;
    }

    private Station createStation(String code, String name, String location) {
        Station s = new Station();
        s.setStationCode(code);
        s.setStationName(name);
        s.setLocation(location);
        s.setActive(true);
        return stationRepository.save(s);
    }

    private Route createRoute(String code, String name, BigDecimal distance) {
        Route r = new Route();
        r.setRouteCode(code);
        r.setRouteName(name);
        r.setTotalDistanceKm(distance);
        r.setActive(true);
        return routeRepository.save(r);
    }

    private RouteStation createRouteStation(Route route, Station station, Integer seq, BigDecimal dist) {
        RouteStation rs = new RouteStation();
        rs.setRoute(route);
        rs.setStation(station);
        rs.setStationSequence(seq);
        rs.setDistanceFromSourceKm(dist);
        return routeStationRepository.save(rs);
    }

    private User createUser(String firstName, String lastName, String email, String phone, UserRole role) {
        User u = new User();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setPassword("Password@123");
        u.setPhoneNumber(phone);
        u.setRole(role);
        u.setActive(true);
        return userRepository.save(u);
    }

    private MetroCard createMetroCard(String cardNumber, BigDecimal balance, User user) {
        MetroCard mc = new MetroCard();
        mc.setCardNumber(cardNumber);
        mc.setBalance(balance);
        mc.setStatus(MetroCardStatus.ACTIVE);
        mc.setUser(user);
        mc.setExpiryDate(LocalDateTime.now().plusYears(2));
        return metroCardRepository.save(mc);
    }

    private Train createTrain(String number, String name, Integer capacity, TrainStatus status, Route route) {
        Train t = new Train();
        t.setTrainNumber(number);
        t.setTrainName(name);
        t.setCapacity(capacity);
        t.setStatus(status);
        t.setRoute(route);
        return trainRepository.save(t);
    }

    private Schedule createSchedule(Train train, Route route, Station src, Station dest, LocalDateTime dep, LocalDateTime arr) {
        Schedule sch = new Schedule();
        sch.setTrain(train);
        sch.setRoute(route);
        sch.setSourceStation(src);
        sch.setDestinationStation(dest);
        sch.setDepartureTime(dep);
        sch.setArrivalTime(arr);
        return scheduleRepository.save(sch);
    }

    private Fare createFare(Station src, Station dest, BigDecimal amount) {
        Fare f = new Fare();
        f.setSourceStation(src);
        f.setDestinationStation(dest);
        f.setAmount(amount);
        return fareRepository.save(f);
    }

    private Ticket createTicket(String number, String qr, BigDecimal amount, TicketStatus status, User user, MetroCard card, Station src, Station dest) {
        Ticket t = new Ticket();
        t.setTicketNumber(number);
        t.setQrCodeData(qr);
        t.setFareAmount(amount);
        t.setStatus(status);
        t.setUser(user);
        t.setMetroCard(card);
        t.setSourceStation(src);
        t.setDestinationStation(dest);
        t.setValidUntil(LocalDateTime.now().plusHours(2));
        if (status == TicketStatus.USED) {
            t.setUsedAt(LocalDateTime.now());
        }
        return ticketRepository.save(t);
    }

    private Payment createPayment(String txnId, BigDecimal amount, PaymentMethod method, PaymentStatus status, Ticket ticket, MetroCard card, User user) {
        Payment p = new Payment();
        p.setTransactionId(txnId);
        p.setAmount(amount);
        p.setPaymentMethod(method);
        p.setPaymentStatus(status);
        p.setTicket(ticket);
        p.setMetroCard(card);
        p.setUser(user);
        return paymentRepository.save(p);
    }

    private MaintenanceIssue createIssue(String title, String desc, MaintenanceType type, MaintenancePriority priority, MaintenanceStatus status, Station station, Train train, User reportedBy) {
        MaintenanceIssue mi = new MaintenanceIssue();
        mi.setTitle(title);
        mi.setDescription(desc);
        mi.setIssueType(type);
        mi.setPriority(priority);
        mi.setStatus(status);
        mi.setStation(station);
        mi.setTrain(train);
        mi.setReportedBy(reportedBy);
        return maintenanceIssueRepository.save(mi);
    }

    @Override
    public void run(String... args) throws Exception {
        if (stationRepository.count() > 0) {
            return;
        }

        // 1. Stations (15 realistic Mumbai Metro stations)
        Station versova = createStation("VERS", "Versova", "Andheri West, Mumbai");
        Station dnNagar = createStation("DNNG", "D.N. Nagar", "Andheri West, Mumbai");
        Station azadNagar = createStation("AZDN", "Azad Nagar", "Andheri West, Mumbai");
        Station andheri = createStation("ANDH", "Andheri", "Andheri East, Mumbai");
        Station weh = createStation("WEH", "Western Express Highway", "Andheri East, Mumbai");
        Station marolNaka = createStation("MARO", "Marol Naka", "Marol, Mumbai");
        Station airportRoad = createStation("AIRP", "Airport Road", "Andheri East, Mumbai");
        Station sakiNaka = createStation("SAKI", "Saki Naka", "Saki Naka, Mumbai");
        Station ghatkopar = createStation("GHAT", "Ghatkopar", "Ghatkopar West, Mumbai");
        Station dahisar = createStation("DAHI", "Dahisar", "Dahisar East, Mumbai");
        Station borivali = createStation("BORI", "Borivali", "Borivali East, Mumbai");
        Station kandivali = createStation("KAND", "Kandivali", "Kandivali East, Mumbai");
        Station malad = createStation("MALD", "Malad", "Malad East, Mumbai");
        Station goregaon = createStation("GORG", "Goregaon", "Goregaon East, Mumbai");
        Station powai = createStation("POWA", "Powai", "Powai, Mumbai");

        // 2. Routes
        Route blueLine = createRoute("LINE_1", "Blue Line 1 (Versova - Ghatkopar)", new BigDecimal("11.40"));
        Route redLine = createRoute("LINE_7", "Red Line 7 (Dahisar - Andheri East)", new BigDecimal("16.50"));
        Route yellowLine = createRoute("LINE_2A", "Yellow Line 2A (Dahisar - D.N. Nagar)", new BigDecimal("18.60"));
        Route greenLine = createRoute("LINE_4", "Green Line 4 (Ghatkopar - Powai)", new BigDecimal("12.20"));
        Route purpleLine = createRoute("LINE_3", "Purple Line 3 (Aqua Line Connector)", new BigDecimal("14.80"));

        // 3. RouteStations
        createRouteStation(blueLine, versova, 1, new BigDecimal("0.00"));
        createRouteStation(blueLine, dnNagar, 2, new BigDecimal("1.20"));
        createRouteStation(blueLine, azadNagar, 3, new BigDecimal("2.60"));
        createRouteStation(blueLine, andheri, 4, new BigDecimal("4.10"));
        createRouteStation(blueLine, weh, 5, new BigDecimal("5.50"));
        createRouteStation(blueLine, marolNaka, 6, new BigDecimal("7.30"));
        createRouteStation(blueLine, airportRoad, 7, new BigDecimal("8.40"));
        createRouteStation(blueLine, sakiNaka, 8, new BigDecimal("9.60"));
        createRouteStation(blueLine, ghatkopar, 9, new BigDecimal("11.40"));

        // 4. Users
        User aarav = createUser("Aarav", "Sharma", "aarav.sharma@example.com", "+919820011223", UserRole.ROLE_PASSENGER);
        User priya = createUser("Priya", "Patel", "priya.patel@example.com", "+919820022334", UserRole.ROLE_PASSENGER);
        User rohan = createUser("Rohan", "Verma", "rohan.verma@example.com", "+919820033445", UserRole.ROLE_PASSENGER);
        User sneha = createUser("Sneha", "Kulkarni", "sneha.kulkarni@example.com", "+919820044556", UserRole.ROLE_ADMIN);
        User aditya = createUser("Aditya", "Mehta", "aditya.mehta@example.com", "+919820055667", UserRole.ROLE_OPERATOR);

        // 5. Metro Cards
        MetroCard card1 = createMetroCard("MC10001", new BigDecimal("500.00"), aarav);
        MetroCard card2 = createMetroCard("MC10002", new BigDecimal("250.50"), priya);
        MetroCard card3 = createMetroCard("MC10003", new BigDecimal("100.00"), rohan);

        // 6. Trains
        Train train1 = createTrain("Metro-101", "Rake Alpha", 1000, TrainStatus.ACTIVE, blueLine);
        Train train2 = createTrain("Metro-102", "Rake Beta", 1000, TrainStatus.ACTIVE, blueLine);
        Train train3 = createTrain("Metro-103", "Rake Gamma", 1200, TrainStatus.ACTIVE, redLine);
        Train train4 = createTrain("Metro-104", "Rake Delta", 1200, TrainStatus.ACTIVE, yellowLine);
        Train train5 = createTrain("Metro-105", "Rake Epsilon", 1000, TrainStatus.MAINTENANCE, greenLine);

        // 7. Schedules
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        createSchedule(train1, blueLine, versova, ghatkopar, today.plusHours(8), today.plusHours(8).plusMinutes(25));
        createSchedule(train1, blueLine, ghatkopar, versova, today.plusHours(12), today.plusHours(12).plusMinutes(25));
        createSchedule(train2, blueLine, versova, ghatkopar, today.plusHours(17), today.plusHours(17).plusMinutes(25));
        createSchedule(train2, blueLine, ghatkopar, versova, today.plusHours(21), today.plusHours(21).plusMinutes(25));

        // 8. Fares
        createFare(versova, andheri, new BigDecimal("20.00"));
        createFare(versova, ghatkopar, new BigDecimal("40.00"));
        createFare(andheri, ghatkopar, new BigDecimal("30.00"));
        createFare(dahisar, andheri, new BigDecimal("50.00"));

        // 9. Tickets
        Ticket ticket1 = createTicket("TCK-2026-001", "QR-DATA-VERS-GHAT-001", new BigDecimal("40.00"), TicketStatus.USED, aarav, card1, versova, ghatkopar);
        Ticket ticket2 = createTicket("TCK-2026-002", "QR-DATA-ANDH-GHAT-002", new BigDecimal("30.00"), TicketStatus.BOOKED, priya, card2, andheri, ghatkopar);
        Ticket ticket3 = createTicket("TCK-2026-003", "QR-DATA-VERS-ANDH-003", new BigDecimal("20.00"), TicketStatus.BOOKED, rohan, card3, versova, andheri);

        // 10. Payments
        createPayment("TXN998877661", new BigDecimal("40.00"), PaymentMethod.METRO_CARD, PaymentStatus.COMPLETED, ticket1, card1, aarav);
        createPayment("TXN998877662", new BigDecimal("30.00"), PaymentMethod.UPI, PaymentStatus.COMPLETED, ticket2, card2, priya);
        createPayment("TXN998877663", new BigDecimal("20.00"), PaymentMethod.METRO_CARD, PaymentStatus.COMPLETED, ticket3, card3, rohan);

        // 11. Maintenance Issues
        createIssue("Escalator #2 Malfunction", "Escalator at Platform 1 requires motor check.", MaintenanceType.STATION_FACILITY, MaintenancePriority.MEDIUM, MaintenanceStatus.OPEN, andheri, null, aditya);
        createIssue("Door Sensor Fault on Rake Epsilon", "Door #4 sensor fails auto-close.", MaintenanceType.TRAIN_MECHANICAL, MaintenancePriority.HIGH, MaintenanceStatus.IN_PROGRESS, null, train5, aditya);
        createIssue("Ticket Vending Machine Offline", "TVM #3 screen frozen at Versova station.", MaintenanceType.STATION_FACILITY, MaintenancePriority.LOW, MaintenanceStatus.OPEN, versova, null, aarav);
    }
}
