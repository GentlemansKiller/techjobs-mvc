package org.launchcode.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="/results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchType.equals("all")) {
            ArrayList<HashMap<String,String>> jobsFoundAll = JobData.findByValue(searchTerm);
            String resultsCount = jobsFoundAll.size() + " Result(s)";
            model.addAttribute("resultsCount", resultsCount);
            model.addAttribute("jobsFound",jobsFoundAll);
            model.addAttribute("columns", ListController.columnChoices);
            return "search";
        }
        else {
            ArrayList<HashMap<String,String>> jobsFoundColumn = JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("jobsFound",jobsFoundColumn);
            String resultsCount = jobsFoundColumn.size() + " Result(s)";
            model.addAttribute("resultsCount", resultsCount);
            model.addAttribute("columns", ListController.columnChoices);
            return "search";
        }
    }

}
