package org.themunthedude.customreportviewer.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.themunthedude.customreportviewer.ReportParser;
import org.themunthedude.customreportviewer.json.ReportJson;

/**
 * Spring Controller Definitions.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String init(Map<String, Object> model, Principal principal) {
        model.put("title", "PUBLIC AREA");
        model.put("message", "Any user can view this page");
        model.put("username", getUserName(principal));
        model.put("userroles", getUserRoles(principal));
        return "home";
    }

    @RequestMapping("/viewReports")
    public String viewReports(Model model, Principal principal) throws IOException {
        Map<String, List<ReportJson>> sortedReports = new HashMap<>();
        List<ReportJson> reps = ReportParser.parseReports("./target/");

        for(ReportJson rep : reps) {
            String groupId = rep.getProjectInfo().getGroupID();
            String artifactId = rep.getProjectInfo().getArtifactID();
            String version = rep.getProjectInfo().getVersion();
            String gav = String.format("%s:%s:%s", groupId, artifactId, version);

            if(sortedReports.containsKey(gav)) {
                sortedReports.get(gav).add(rep);
            } else {
                List<ReportJson> reportsForGAV = new ArrayList<>();
                reportsForGAV.add(rep);
                sortedReports.put(gav, reportsForGAV);
            }
        }

        model.addAttribute("projects", sortedReports);

        return "viewReports";
    }

    private String getUserName(Principal principal) {
        if (principal == null) {
            return "anonymous";
        } else {

            final UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                System.out.println(grantedAuthority.getAuthority());
            }
            return principal.getName();
        }
    }

    private Collection<String> getUserRoles(Principal principal) {
        if (principal == null) {
            return Arrays.asList("none");
        } else {

            Set<String> roles = new HashSet<String>();

            final UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                roles.add(grantedAuthority.getAuthority());
            }
            return roles;
        }
    }

}
