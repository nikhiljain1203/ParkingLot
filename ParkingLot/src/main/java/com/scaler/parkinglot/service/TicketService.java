package com.scaler.parkinglot.service;

import com.scaler.parkinglot.exceptions.SpotNotFoundException;
import com.scaler.parkinglot.models.Gate;
import com.scaler.parkinglot.models.ParkingSpot;
import com.scaler.parkinglot.models.Ticket;
import com.scaler.parkinglot.models.Vehicle;
import com.scaler.parkinglot.models.VehicleType;
import com.scaler.parkinglot.repositories.TicketRepository;
import com.scaler.parkinglot.strategies.spotassignment.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {

    private VehicleService vehicleService;
    private GateService gateService;
    private TicketRepository ticketRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;

    public TicketService(VehicleService vehicleService, GateService gateService, TicketRepository ticketRepository, SpotAssignmentStrategy spotAssignmentStrategy) {
        this.vehicleService = vehicleService;
        this.gateService = gateService;
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, long gateId)
        throws SpotNotFoundException {

        // Check if the vehicle present in the DB
        // 1. VehicleService - getVehicleByNumber - recommened Approach
        // 2. VehicleRepo - fetchByNumber

        // get or create the vehicle
        // create ticket

        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);
        if(vehicle == null) {
            vehicle = vehicleService.registerVehicle(vehicleNumber, vehicleType);
        }

        Gate gate = gateService.getGateById(gateId);

        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setOperator(gate.getOperator());
        ticket.setEntryTime(new Date());

        ParkingSpot spot = spotAssignmentStrategy.assignSpot(vehicleType, gate);

        if(spot == null) {
            throw new SpotNotFoundException("Spot Not found");
        }

        ticket.setParkingSpot(spot);

        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }
}
