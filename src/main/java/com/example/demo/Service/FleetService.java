package com.example.demo.Service;

import com.example.demo.DBManager.DatabaseException;
import com.example.demo.Model.BookedMotorhome;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Order;
import com.example.demo.Repository.FleetRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FleetService {

    FleetRepository fleetRepository = new FleetRepository();

    public ArrayList<Motorhome> getAllMotorhomes() {
        return fleetRepository.getAllMotorhomes();
    }

    public Motorhome getMotorhome(Order order) {
        return fleetRepository.getMotorhomeInfo(order.getMotorhomeId());
    }

    public void checkAvailibility() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        Date date = new Date();
        String today = formatter.format(date);
        ArrayList<BookedMotorhome> bookedArray = fleetRepository.getAllBookedHomes();
        for (BookedMotorhome booked : bookedArray) {
            if (booked.getEndDate().toString().equalsIgnoreCase(today)){
                Motorhome motorhome = fleetRepository.getMotorhome(booked.getMotorhomeId());
                fleetRepository.removeBookedHome(booked);
            }
        }
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
}
