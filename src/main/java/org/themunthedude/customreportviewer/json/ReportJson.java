package org.themunthedude.customreportviewer.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reportSchema",
        "scanInfo",
        "projectInfo",
        "dependencies"
})
public class ReportJson {

    @JsonProperty("reportSchema")
    private String reportSchema;
    @JsonProperty("scanInfo")
    private ScanInfo scanInfo;
    @JsonProperty("projectInfo")
    private ProjectInfo projectInfo;
    @JsonProperty("dependencies")
    private List<Dependency> dependencies = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private static final float HIGH_THRESHOLD = 7.5f; // Inclusive
    private static final float MEDIUM_THRESHOLD = 5.0f; // Inclusive

    @JsonProperty("reportSchema")
    public String getReportSchema() {
        return reportSchema;
    }

    @JsonProperty("reportSchema")
    public void setReportSchema(String reportSchema) {
        this.reportSchema = reportSchema;
    }

    @JsonProperty("scanInfo")
    public ScanInfo getScanInfo() {
        return scanInfo;
    }

    @JsonProperty("scanInfo")
    public void setScanInfo(ScanInfo scanInfo) {
        this.scanInfo = scanInfo;
    }

    @JsonProperty("projectInfo")
    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    @JsonProperty("projectInfo")
    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    @JsonProperty("dependencies")
    public List<Dependency> getDependencies() {
        return dependencies;
    }

    @JsonProperty("dependencies")
    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public int getNumHigh() {
        int count = 0;

        for(Dependency dep : dependencies) {
            if(dep != null) {
                for(Vulnerability vuln : dep.getVulnerabilities()) {
                    double score;

                    if(vuln.getCvssv3() != null) {
                        score = vuln.getCvssv3().getBaseScore();
                    } else if (vuln.getCvssv2() != null){
                        score = vuln.getCvssv2().getScore();
                    } else {
                        score = 0.0;
                    }

                    if(score >= HIGH_THRESHOLD) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int getNumMedium() {
        int count = 0;

        for(Dependency dep : dependencies) {
            if(dep != null) {
                for (Vulnerability vuln : dep.getVulnerabilities()) {
                    double score;

                    if(vuln.getCvssv3() != null) {
                        score = vuln.getCvssv3().getBaseScore();
                    } else if (vuln.getCvssv2() != null){
                        score = vuln.getCvssv2().getScore();
                    } else {
                        score = 0.0;
                    }

                    if (score < HIGH_THRESHOLD && score >= MEDIUM_THRESHOLD) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int getNumLow() {
        int count = 0;

        for(Dependency dep : dependencies) {
            if(dep != null) {
                for (Vulnerability vuln : dep.getVulnerabilities()) {
                    double score;

                    if(vuln.getCvssv3() != null) {
                        score = vuln.getCvssv3().getBaseScore();
                    } else if (vuln.getCvssv2() != null){
                        score = vuln.getCvssv2().getScore();
                    } else {
                        score = 0.0;
                    }

                    if (score < MEDIUM_THRESHOLD) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

}