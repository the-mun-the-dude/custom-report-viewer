package org.themunthedude.customreportviewer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.themunthedude.customreportviewer.json.ReportJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReportParser {
    private static final String REPORT_REGEX = "[\\d\\D]*dependency-check-report\\.json";

    public static List<ReportJson> parseReports(String rootFolderPath) throws IOException {
        File rootFolder = new File(rootFolderPath);
        File[] reportFiles = rootFolder.listFiles();
        List<ReportJson> reports = new ArrayList<ReportJson>();

        if(reportFiles != null) {
            for(File reportFile : reportFiles) {
                if(reportFile.getName().matches(REPORT_REGEX)) {
                    reports.add(parseReport(reportFile));
                }
            }
        }

        return reports;
    }

    private static ReportJson parseReport(File file) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        ObjectMapper mapper = new ObjectMapper();
        ReportJson rep = mapper.readValue(jsonData, ReportJson.class);

        return rep;
    }
}
