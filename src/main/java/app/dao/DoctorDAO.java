package app.dao;

import app.exception.DoctorEnabledException;
import app.exception.NoFoundDoctorException;
import org.springframework.stereotype.Component;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Doctor;
import preprod.qa.soap.Specialization;
import preprod.qa.soap.Status;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User:Anton_Iehorov
 * Date: 12/23/2016
 * Time: 10:42 AM
 */
@Component
public class DoctorDAO {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    //TODO should divide into classes. Class has a lot of responsibility
    {
        Doctor doctor = new Doctor();
        doctor.setName("Peter");
        doctor.setSpecialization(Specialization.DENTIST);
        doctors.add(doctor);

        Doctor doctorSecond = new Doctor();
        doctorSecond.setName("Greg");
        doctorSecond.setSpecialization(Specialization.NEUROSURGEONS);
        doctors.add(doctorSecond);


        Booking booking = new Booking();
        booking.setDoctorName(doctor.getName());
        booking.setBookingStatus(Status.ACTIVE);
        booking.setTimeSlot(new BigInteger("11"));
        bookings.add(booking);

        Booking bookingThird = new Booking();
        bookingThird.setDoctorName(doctor.getName());
        bookingThird.setBookingStatus(Status.ACTIVE);
        bookingThird.setTimeSlot(new BigInteger("12"));
        bookings.add(bookingThird);

        Booking bookingFourth = new Booking();
        bookingFourth.setDoctorName(doctor.getName());
        bookingFourth.setBookingStatus(Status.ACTIVE);
        bookingFourth.setTimeSlot(new BigInteger("13"));
        bookings.add(bookingFourth);

        Booking bookingSecond = new Booking();
        bookingSecond.setDoctorName(doctorSecond.getName());
        bookingSecond.setBookingStatus(Status.CLOSED);
        bookingSecond.setTimeSlot(new BigInteger("5"));
        bookings.add(bookingSecond);
    }

    public List<Booking> getDoctorsByFreeTime(int startTime, int endTime, String doctorsName) {

        //TODO rewrite into 2 statements to throw correct error
        Optional<Booking> bookingWithDoctorName = bookings.stream().
                filter(bookings -> bookings.getDoctorName().equals(doctorsName)).findAny();

        if (!bookingWithDoctorName.isPresent())
            throw new NoFoundDoctorException("Cannot find doctor by its name!");
        else {
            List<Booking> bookingToFind = bookings.stream().
                    filter(bookings -> startTime < bookings.getTimeSlot().intValue()
                            && endTime > bookings.getTimeSlot().intValue()
                            && bookings.getBookingStatus().equals(Status.ACTIVE)).collect(Collectors.toList());
            if(!bookingToFind.isEmpty()) return bookingToFind;
        }
        throw new DoctorEnabledException("There is no free booking at this time!");
    }

    public Booking addBooking(Booking booking) {
        for (Booking bookingToFind : bookings) {
            if (bookingToFind.getDoctorName().equals(booking.getDoctorName())) {
                if (bookingToFind.getTimeSlot().equals(booking.getTimeSlot())) {
                    if (bookingToFind.getBookingStatus().equals(Status.ACTIVE)) {
                        bookingToFind.setBookingStatus(Status.CLOSED);
                        bookings.add(booking);
                        return booking;
                    }
                    throw new DoctorEnabledException("Booking is closed!");
                }
                throw new DoctorEnabledException("There is no booking at this time!");
            }
        }

        throw new NoFoundDoctorException("Cannot find doctor by its name!");

    }

    public Booking cancelBooking(Booking booking) {
        Optional<Booking> bookingSearch =
                bookings.stream().filter(bookings -> bookings.getDoctorName().equals(booking.getDoctorName()) &&
                        bookings.getTimeSlot().equals(booking.getTimeSlot())).findAny();

        if (bookingSearch.isPresent()) {
            bookings.remove(bookingSearch.get());
            return booking;
        } else throw new IllegalArgumentException("Invalid booking cancel!");

    }

    public Doctor getDoctorsBySpecial(Specialization specialization) {
        Optional<Doctor> doctor = doctors.stream().
                filter(doctors -> specialization.equals(doctors.getSpecialization())).findAny();
        return doctor.isPresent() ? doctor.get() : null;
    }
}
