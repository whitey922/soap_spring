package app.rest;

import app.service.ScpecializtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import preprod.qa.soap.AddBookingResponse;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Status;

/**
 * User:Anton_Iehorov
 * Date: 12/26/2016
 * Time: 4:51 PM
 */

@Controller

@RequestMapping(value = "/doctors/addbooking")
public class AddBookingController {

    private ScpecializtionService scpecializtionService;

    @Autowired
    public AddBookingController(ScpecializtionService scpecializtionService) {
        this.scpecializtionService = scpecializtionService;
    }


    @RequestMapping(method = RequestMethod.POST,  headers="Accept=application/xml")
    @ResponseBody
    public AddBookingResponse addBooking(@RequestBody Booking booking) {
        AddBookingResponse response = new AddBookingResponse();
        Booking bookingResponse = new Booking();
        bookingResponse.setDoctorName(booking.getDoctorName());
        bookingResponse.setTimeSlot(booking.getTimeSlot());
        bookingResponse.setBookingStatus(Status.CLOSED);

        response.setBooking(scpecializtionService.addBooking(booking));

        return response;
    }
}