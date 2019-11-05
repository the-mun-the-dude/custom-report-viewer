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
        "isVirtual",
        "filePath",
        "sha256",
        "sha1",
        "md5",
        "packageIds"
})
public class RelatedDependency {

    @JsonProperty("isVirtual")
    private Boolean isVirtual;
    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("sha256")
    private String sha256;
    @JsonProperty("sha1")
    private String sha1;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("packageIds")
    private List<PackageId> packageIds = null;
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

    @JsonProperty("filePath")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("filePath")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @JsonProperty("sha256")
    public String getSha256() {
        return sha256;
    }

    @JsonProperty("sha256")
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @JsonProperty("sha1")
    public String getSha1() {
        return sha1;
    }

    @JsonProperty("sha1")
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    @JsonProperty("md5")
    public String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @JsonProperty("packageIds")
    public List<PackageId> getPackageIds() {
        return packageIds;
    }

    @JsonProperty("packageIds")
    public void setPackageIds(List<PackageId> packageIds) {
        this.packageIds = packageIds;
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