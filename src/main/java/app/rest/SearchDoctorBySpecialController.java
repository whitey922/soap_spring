package app.rest;

import app.service.ScpecializtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import preprod.qa.soap.SearchDoctorBySpecialResponse;
import preprod.qa.soap.Specialization;

/**
 * User:Anton_Iehorov
 * Date: 12/27/2016
 * Time: 11:20 AM
 */
@Controller
@RequestMapping(value = "/doctors/byspecialization")
public class SearchDoctorBySpecialController {

    private ScpecializtionService scpecializtionService;

    @Autowired
    public SearchDoctorBySpecialController(ScpecializtionService scpecializtionService) {
        this.scpecializtionService = scpecializtionService;
    }


    @RequestMapping(method = RequestMethod.GET,  headers="Accept=application/xml")
    @ResponseBody
    public SearchDoctorBySpecialResponse getDoctorsBySpecial(@RequestParam (name= "specialization") Specialization specialization) {
        SearchDoctorBySpecialResponse response = new SearchDoctorBySpecialResponse();
        response.setDoctor(scpecializtionService.getDoctorsBySpecial(specialization));

        return response;
    }
}
