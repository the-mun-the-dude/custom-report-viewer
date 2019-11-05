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
        "id",
        "vulnerabilityIdMatched"
})
public class Software {

    @JsonProperty("id")
    private String id;
    @JsonProperty("vulnerabilityIdMatched")
    private String vulnerabilityIdMatched;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("vulnerabilityIdMatched")
    public String getVulnerabilityIdMatched() {
        return vulnerabilityIdMatched;
    }

    @JsonProperty("vulnerabilityIdMatched")
    public void setVulnerabilityIdMatched(String vulnerabilityIdMatched) {
        this.vulnerabilityIdMatched = vulnerabilityIdMatched;
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