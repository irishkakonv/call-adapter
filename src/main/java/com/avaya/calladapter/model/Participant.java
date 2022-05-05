package com.avaya.calladapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * calling participant
 */

@Schema(name = "Participant", description = "calling participant")
public class Participant {

  @JsonProperty("id")
  @NotBlank(message = "participant.id is mandatory")
  private String id;

  @JsonProperty("name")
  @NotBlank(message = "participant.name is mandatory")
  private String name;

  public Participant id(String id) {
    this.id = id;
    return this;
  }

  /**
   * participant id
   * @return id
  */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Participant name(String name) {
    this.name = name;
    return this;
  }

  /**
   * participant autodetected name
   * @return name
  */
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Participant participant = (Participant) o;
    return Objects.equals(this.id, participant.id) &&
        Objects.equals(this.name, participant.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Participant {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

