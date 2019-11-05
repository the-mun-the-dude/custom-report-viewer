package org.themunthedude.customreportviewer.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.ParseException;
import java.util.*;

import org.jfree.chart.JFreeChart;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.themunthedude.customreportviewer.ReportParser;
import org.themunthedude.customreportviewer.json.ReportJson;
import org.themunthedude.customreportviewer.visualisation.ChartBuilder;

import javax.imageio.ImageIO;

/**
 * Spring Controller Definitions.
 */
@Controller
public class MainController {
    Map<String, List<ReportJson>> sortedReports = new HashMap<>();

    @RequestMapping("/")
    public String init(Map<String, Object> model, Principal principal) {
        model.put("title", "PUBLIC AREA");
        model.put("message", "Any user can view this page");
        model.put("username", getUserName(principal));
        model.put("userroles", getUserRoles(principal));
        return "home";
    }

    // TODO: put in a button on each project listing, which pops up a modal window with a graph showing CVEs over time
    @RequestMapping("/viewReports")
    public String viewReports(Model model, Principal principal) throws IOException {
        sortedReports = new HashMap<>();
        List<ReportJson> reps = ReportParser.parseReports("./target/");

        for(ReportJson rep : reps) {
            String artifactId = rep.getProjectInfo().getArtifactID();
            artifactId = artifactId.replaceAll("\\.", "_");

            if(sortedReports.containsKey(artifactId)) {
                sortedReports.get(artifactId).add(rep);
            } else {
                List<ReportJson> reportsForGAV = new ArrayList<>();
                reportsForGAV.add(rep);
                sortedReports.put(artifactId, reportsForGAV);
            }
        }

        model.addAttribute("projects", sortedReports);

        return "viewReports";
    }

    @GetMapping(value = "/getChart/{gav}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getChart(@PathVariable("gav")String gav) throws ParseException, IOException {
        System.out.println("Creating Chart...");
        byte[] imageInBytes = null;

        if(sortedReports.containsKey(gav)) {
            List<ReportJson> reports = sortedReports.get(gav);
            JFreeChart chart = ChartBuilder.createChart(gav, reports);
            BufferedImage image = ChartBuilder.extractImage(chart, 1920, 1080);

            System.out.println("Sending Chart...");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            imageInBytes = baos.toByteArray();
        } else {
            throw new ParseException(String.format("GAV Parameter %s does not Match any Reports", gav), 0);
        }

        return imageInBytes;
    }

    private String getUserName(Principal principal) {
        if (principal == null) {
            return "anonymous";
        } else {
            final UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();

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
