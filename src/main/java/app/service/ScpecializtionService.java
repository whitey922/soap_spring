package app.service;

import app.dao.DoctorDAO;
import org.springframework.stereotype.Component;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Doctor;
import preprod.qa.soap.Specialization;

import java.util.List;

/**
 * User:Anton_Iehorov
 * Date: 12/23/2016
 * Time: 10:49 AM
 */
@Component
public class ScpecializtionService {
    private DoctorDAO doctorDAO = new DoctorDAO();

    public Doctor getDoctorsBySpecial(Specialization specialization) {
        if (specialization == null) {
            throw new IllegalArgumentException("Invalid specialization!");
        }
        return doctorDAO.getDoctorsBySpecial(specialization);
    }


    public Booking addBooking(Booking booking) {
        return doctorDAO.addBooking(booking);
    }

    public Booking cancelBooking(Booking booking) {
        return doctorDAO.cancelBooking(booking);
    }
    public List<Booking> getDoctorsByFreeTime(int startTime, int endTime, String doctorsName) {
        return doctorDAO.getDoctorsByFreeTime(startTime, endTime, doctorsName);
    }

//    public Booking addBooking(Booking booking) {
//        if (booking == null) {
//            throw new IllegalArgumentException("Invalid booking!");
//        }
//        return doctorDAO.addBooking(booking);
//    }
//
//    public Booking cancelBooking(Booking booking) {
//        if (booking == null) {
//            throw new IllegalArgumentException("Invalid cancel!");
//        }
//        return doctorDAO.cancelBooking(booking);
//    }

}
