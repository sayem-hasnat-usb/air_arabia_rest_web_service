package com.firsttrip.demo.controller;

import com.firsttrip.demo.request.SearchRequest;
import com.firsttrip.demo.service.AvailabilitySearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/air-arabia")
public class AvailabilitySearchController {

    public final AvailabilitySearchService availabilitySearchService;

    public AvailabilitySearchController(AvailabilitySearchService availabilitySearchService) {
        this.availabilitySearchService = availabilitySearchService;
    }

    @PostMapping("/search")
    public Object getAvailability(@RequestBody SearchRequest searchRequest){

        return availabilitySearchService.getAvailability(searchRequest);
    }
}
