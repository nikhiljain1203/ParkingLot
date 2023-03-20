package com.scaler.parkinglot;

import com.scaler.parkinglot.controllers.TicketController;
import com.scaler.parkinglot.dto.GenerateTicketRequestDto;
import com.scaler.parkinglot.dto.GenerateTicketResponse;
import com.scaler.parkinglot.models.Ticket;
import com.scaler.parkinglot.models.VehicleType;
import com.scaler.parkinglot.repositories.ParkingLotRepository;
import com.scaler.parkinglot.repositories.TicketRepository;
import com.scaler.parkinglot.service.GateService;
import com.scaler.parkinglot.service.TicketService;
import com.scaler.parkinglot.service.VehicleService;
import com.scaler.parkinglot.strategies.spotassignment.RandomSpotAssignmentStrategy;
import com.scaler.parkinglot.strategies.spotassignment.SpotAssignmentStrategy;

// Will Start at 9.10
public class Main {
    public static void main(String[] args) {

        ObjectRegistry objectRegistry = new ObjectRegistry();
        VehicleService vehicleService = new VehicleService();
        GateService gateService = new GateService();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingLotRepository);
        TicketService ticketService = new TicketService(
            vehicleService, gateService, ticketRepository, spotAssignmentStrategy
        );
        TicketController ticketController = new TicketController(ticketService);

        objectRegistry.register("ticketService", ticketService);

        GenerateTicketRequestDto requestDto = new GenerateTicketRequestDto();
        requestDto.setVehicleType(VehicleType.LARGE);
        requestDto.setGateId(1l);
        requestDto.setVehicleNumber("KA-01-HH-1234");

        GenerateTicketResponse generateTicketResponse = ticketController.generateTicket(requestDto);

    }
}

// Requirements
// 1. Operator should be able to generate the ticket

//MVC
//Controller
//Services
//Repository


// Assignment
// 1. Create a ParkingLot
// 2. Add gates in the parking lot
// 3. Create an operator
// 4. Assign an operator to a gate
// 5. [BUGFIX] Mark parking spot occupied on assignment



