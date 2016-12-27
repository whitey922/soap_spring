package app.rest;

import app.service.ScpecializtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import preprod.qa.soap.Booking;
import preprod.qa.soap.CancelBookingResponse;

/**
 * User:Anton_Iehorov
 * Date: 12/27/2016
 * Time: 11:14 AM
 */

@Controller

@RequestMapping(value = "/doctors/cancelbooking")
public class CancelBookingRequestController  {


    private ScpecializtionService scpecializtionService;

    @Autowired
    public CancelBookingRequestController(ScpecializtionService scpecializtionService) {
        this.scpecializtionService = scpecializtionService;
    }


    @RequestMapping(method = RequestMethod.POST,  headers="Accept=application/xml")
    @ResponseBody
    public CancelBookingResponse cancelBooking(@RequestBody Booking booking) {
        CancelBookingResponse response = new CancelBookingResponse();
        Booking bookingResponse = new Booking();
        bookingResponse.setDoctorName(booking.getDoctorName());
        bookingResponse.setTimeSlot(booking.getTimeSlot());

        response.setBooking(scpecializtionService.cancelBooking(booking));

        return response;
    }
}
