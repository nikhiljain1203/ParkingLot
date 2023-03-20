package com.scaler.parkinglot.strategies.spotassignment;

import com.scaler.parkinglot.models.*;
import com.scaler.parkinglot.repositories.ParkingLotRepository;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {

    private ParkingLotRepository parkingLotRepository;

    public RandomSpotAssignmentStrategy(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
//        ParkingLot parkingLot = ParkingLotRepository.getParkingLotWithGate();
//        List<ParkingSpot> parkingSpots = ParkingSpotRepository.getParkingSpotsByLot();
//
//        for (ParkingSpot parkingSpot: parkingSpots) {
//            if (parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)
//            && parkingSpot.getSupportedVehicleTypes().contains(vehicleType)) {
//                return parkingSpot;
//            }
//        }
//        return null;

        return null;
    }
}
