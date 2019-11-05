package org.themunthedude.customreportviewer.json;

import java.util.ArrayList;
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
        "isVirtual",
        "fileName",
        "filePath",
        "md5",
        "sha1",
        "sha256",
        "description",
        "license",
        "projectReferences",
        "relatedDependencies",
        "evidenceCollected",
        "packages",
        "vulnerabilityIds",
        "vulnerabilities"
})
public class Dependency {

    @JsonProperty("isVirtual")
    private Boolean isVirtual;
    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("sha1")
    private String sha1;
    @JsonProperty("sha256")
    private String sha256;
    @JsonProperty("description")
    private String description;
    @JsonProperty("license")
    private String license;
    @JsonProperty("projectReferences")
    private List<String> projectReferences = null;
    @JsonProperty("relatedDependencies")
    private List<RelatedDependency> relatedDependencies = null;
    @JsonProperty("evidenceCollected")
    private EvidenceCollected evidenceCollected;
    @JsonProperty("packages")
    private List<Package> packages = null;
    @JsonProperty("vulnerabilityIds")
    private List<VulnerabilityId> vulnerabilityIds = null;
    @JsonProperty("vulnerabilities")
    private List<Vulnerability> vulnerabilities = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("isVirtual")
    public Boolean getIsVirtual() {
        return isVirtual;
    }

    @JsonProperty("isVirtual")
    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    @JsonProperty("fileName")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JsonProperty("filePath")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("filePath")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @JsonProperty("md5")
    public String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @JsonProperty("sha1")
    public String getSha1() {
        return sha1;
    }

    @JsonProperty("sha1")
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    @JsonProperty("sha256")
    public String getSha256() {
        return sha256;
    }

    @JsonProperty("sha256")
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("projectReferences")
    public List<String> getProjectReferences() {
        return projectReferences;
    }

    @JsonProperty("projectReferences")
    public void setProjectReferences(List<String> projectReferences) {
        this.projectReferences = projectReferences;
    }

    @JsonProperty("relatedDependencies")
    public List<RelatedDependency> getRelatedDependencies() {
        return relatedDependencies;
    }

    @JsonProperty("relatedDependencies")
    public void setRelatedDependencies(List<RelatedDependency> relatedDependencies) {
        this.relatedDependencies = relatedDependencies;
    }

    @JsonProperty("evidenceCollected")
    public EvidenceCollected getEvidenceCollected() {
        return evidenceCollected;
    }

    @JsonProperty("evidenceCollected")
    public void setEvidenceCollected(EvidenceCollected evidenceCollected) {
        this.evidenceCollected = evidenceCollected;
    }

    @JsonProperty("packages")
    public List<Package> getPackages() {
        return packages;
    }

    @JsonProperty("packages")
    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    @JsonProperty("vulnerabilityIds")
    public List<VulnerabilityId> getVulnerabilityIds() {
        return vulnerabilityIds;
    }

    @JsonProperty("vulnerabilityIds")
    public void setVulnerabilityIds(List<VulnerabilityId> vulnerabilityIds) {
        this.vulnerabilityIds = vulnerabilityIds;
    }

    @JsonProperty("vulnerabilities")
    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    @JsonProperty("vulnerabilities")
    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
