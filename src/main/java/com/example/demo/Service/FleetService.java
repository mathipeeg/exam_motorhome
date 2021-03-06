package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.FleetRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FleetService {

    FleetRepository fleetRepository = new FleetRepository();

    public ArrayList<Motorhome> getAllMotorhomes() {
        return fleetRepository.getAllMotorhomes();
    }

    public Motorhome getMotorhome(Order order) {
        return fleetRepository.getMotorhomeInfo(order.getMotorhomeId());
    }

    public ArrayList<BookedMotorhome> getAllBookedMotorhomes() {
        return fleetRepository.getAllBookedHomes();
    }

    public void submitBookedHome(CustomerOrder customerOrder) {
        BookedMotorhome bookedMotorhome = new BookedMotorhome();
        bookedMotorhome.setMotorhomeId(customerOrder.getMotorhomeId());
        bookedMotorhome.setStartDate(customerOrder.getStartDate());
        bookedMotorhome.setEndDate(customerOrder.getEndDate());
        fleetRepository.bookMotorhome(bookedMotorhome);
    }

    public void checkExpiredBookings() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = formatter.format(date);
        ArrayList<BookedMotorhome> bookedArray = fleetRepository.getAllBookedHomes();
        for (BookedMotorhome booked : bookedArray) {
            if (formatter.format(booked.getEndDate()).equalsIgnoreCase(currentDate)){
                fleetRepository.removeBookedHome(booked);
            }
        }
    }
}
