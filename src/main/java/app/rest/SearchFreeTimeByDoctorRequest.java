package app.rest;

import app.service.ScpecializtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import preprod.qa.soap.SearchFreeTimeByDoctorResponse;

/**
 * User:Anton_Iehorov
 * Date: 12/27/2016
 * Time: 1:13 PM
 */

@Controller
@RequestMapping(value = "/doctors/byfreetime")
public class SearchFreeTimeByDoctorRequest {

    private ScpecializtionService scpecializtionService;

    @Autowired
    public SearchFreeTimeByDoctorRequest(ScpecializtionService scpecializtionService) {
        this.scpecializtionService = scpecializtionService;
    }


    @RequestMapping(method = RequestMethod.GET,  headers="Accept=application/xml")
    @ResponseBody
    public SearchFreeTimeByDoctorResponse getDoctorsBySpecial
            (@RequestParam(name= "doctorName") String doctorName,
             @RequestParam(name= "startTime") int startTime,
             @RequestParam(name= "endTime") int endTime) {
        SearchFreeTimeByDoctorResponse response = new SearchFreeTimeByDoctorResponse();
        response.getName().addAll(scpecializtionService.getDoctorsByFreeTime(startTime, endTime, doctorName));

        return response;
    }
}
