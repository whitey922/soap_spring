package app.web;

import app.service.ScpecializtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import preprod.qa.soap.*;

/**
 * User:Anton_Iehorov
 * Date: 12/23/2016
 * Time: 11:39 AM
 */
@Endpoint
public class Hospital {
    private static final String NAMESPACE_URI = "http://preprod/qa/soap";

    private ScpecializtionService scpecializtionService;

    @Autowired
    public Hospital(ScpecializtionService scpecializtionService) {
        this.scpecializtionService = scpecializtionService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchDoctorBySpecialRequest")
    @ResponsePayload
    public SearchDoctorBySpecialResponse getSpecialists(@RequestPayload SearchDoctorBySpecialRequest request) {
        SearchDoctorBySpecialResponse response = new SearchDoctorBySpecialResponse();
        response.setDoctor(scpecializtionService.getDoctorsBySpecial(request.getSpecialization()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookingRequest")
    @ResponsePayload
    public AddBookingResponse addBooking(@RequestPayload AddBookingRequest request) {
        AddBookingResponse response = new AddBookingResponse();
        Booking booking = new Booking();
        booking.setDoctorName(request.getDoctorName());
        booking.setTimeSlot(request.getTimeSlot());

        response.setBooking(scpecializtionService.addBooking(booking));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "cancelBookingRequest")
    @ResponsePayload
    public CancelBookingResponse cancelBooking(@RequestPayload CancelBookingRequest request) {
        CancelBookingResponse response = new CancelBookingResponse();

        Booking booking = new Booking();
        booking.setDoctorName(request.getDoctorName());
        booking.setTimeSlot(request.getTimeSlot());

        response.setBooking(scpecializtionService.cancelBooking(booking));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchFreeTimeByDoctorRequest")
    @ResponsePayload
    public SearchFreeTimeByDoctorResponse getDoctorsByFreeTime(@RequestPayload SearchFreeTimeByDoctorRequest request) {
        SearchFreeTimeByDoctorResponse response = new SearchFreeTimeByDoctorResponse();

        response.getName().addAll(scpecializtionService.getDoctorsByFreeTime(
                request.getTimeSlotStart().intValue(),
                request.getTimeSlotFinish().intValue(),
                request.getDoctorName()));
        return response;
    }


}
