package org.themunthedude.customreportviewer.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "NVD",
        "NPM",
        "RETIREJS",
        "OSSINDEX"
})
public class Credits {

    @JsonProperty("NVD")
    private String nvd;
    @JsonProperty("NPM")
    private String npm;
    @JsonProperty("RETIREJS")
    private String retireJS;
    @JsonProperty("OSSINDEX")
    private String ossIndex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NVD")
    public String getNVD() {
        return nvd;
    }

    @JsonProperty("NVD")
    public void setNVD(String nVD) {
        this.nvd = nVD;
    }

    @JsonProperty("NPM")
    public String getNPM() {
        return npm;
    }

    @JsonProperty("NPM")
    public void setNPM(String nPM) {
        this.npm = nPM;
    }

    @JsonProperty("RETIREJS")
    public String getRETIREJS() {
        return retireJS;
    }

    @JsonProperty("RETIREJS")
    public void setRETIREJS(String rETIREJS) {
        this.retireJS = rETIREJS;
    }

    @JsonProperty("OSSINDEX")
    public String getOSSINDEX() {
        return ossIndex;
    }

    @JsonProperty("OSSINDEX")
    public void setOSSINDEX(String oSSINDEX) {
        this.ossIndex = oSSINDEX;
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
