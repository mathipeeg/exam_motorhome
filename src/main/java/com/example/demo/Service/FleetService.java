package com.example.demo.Service;

import com.example.demo.DBManager.DatabaseException;
import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Order;
import com.example.demo.Repository.FleetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FleetService {

    FleetRepository fleetRepository;

    public ArrayList<Motorhome> getAllMotorhomes() {
        return fleetRepository.getAllMotorhomes();
    }

    public Motorhome getMotorhome(Order order) {
        return fleetRepository.getMotorhomeInfo(order.getMotorhomeId());
    }
}
