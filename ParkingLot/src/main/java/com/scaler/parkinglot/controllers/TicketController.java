package com.scaler.parkinglot.controllers;

import com.scaler.parkinglot.dto.GenerateTicketRequestDto;
import com.scaler.parkinglot.dto.GenerateTicketResponse;
import com.scaler.parkinglot.dto.ResponseStatus;
import com.scaler.parkinglot.exceptions.SpotNotFoundException;
import com.scaler.parkinglot.models.Ticket;
import com.scaler.parkinglot.service.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }


    // 1. Get Vehicle
    // 2. Generate Ticket


    // Ticket Service - Generate Ticket
    // VehicleService - Get Vehicle By Number
    // VehicleService - Create Vehicle
    // assign Spot
    // createTicketAndStoreInDB

    public GenerateTicketResponse generateTicket(GenerateTicketRequestDto generateTicketRequestDto){
        try {
            Ticket ticket = ticketService.generateTicket(generateTicketRequestDto.getVehicleNumber(),
                generateTicketRequestDto.getVehicleType(),
                generateTicketRequestDto.getGateId());

            GenerateTicketResponse generateTicketResponse = new GenerateTicketResponse();
            generateTicketResponse.setTicket(ticket);
            generateTicketResponse.setResponseStatus(ResponseStatus.SUCCESS);
            return generateTicketResponse;
        } catch (SpotNotFoundException e) {
            GenerateTicketResponse generateTicketResponse = new GenerateTicketResponse();
            generateTicketResponse.setResponseStatus(ResponseStatus.FAILURE);
            return generateTicketResponse;
        }
    }
}

// DTO - Data Transfer Object

// Get Vehicle from vehicle service
// if not available ask vehicle service to create the vehicle
// assign spot
// ticket service generate ticket
