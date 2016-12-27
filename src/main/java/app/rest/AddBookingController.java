package app.rest;

import app.service.ScpecializtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import preprod.qa.soap.AddBookingResponse;
import preprod.qa.soap.Booking;

import java.math.BigInteger;

/**
 * User:Anton_Iehorov
 * Date: 12/26/2016
 * Time: 4:51 PM
 */

@Controller

@RequestMapping(value = "/doctors")
public class AddBookingController {

    private ScpecializtionService scpecializtionService;

    @Autowired
    public AddBookingController(ScpecializtionService scpecializtionService) {
        this.scpecializtionService = scpecializtionService;
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public AddBookingResponse addBooking(@RequestParam(name = "doctorName") String doctorName,
                                         @RequestParam(name = "timeSlot") BigInteger timeSlot) {
        AddBookingResponse response = new AddBookingResponse();
        Booking booking = new Booking();
        booking.setDoctorName(doctorName);
        booking.setTimeSlot(timeSlot);

        response.setBooking(scpecializtionService.addBooking(booking));

        return response;
    }
}