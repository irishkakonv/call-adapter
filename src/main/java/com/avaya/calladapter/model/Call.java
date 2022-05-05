package com.avaya.calladapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Call
 */
public class Call {

  @JsonProperty("id")
  @Size(min = 36, max = 36, message = "Id must contains 36 characters")
  private String id;

  @JsonProperty("callerNumber")
  @NotBlank(message = "callerNumber is mandatory")
  private String callerNumber;

  @JsonProperty("calledNumber")
  @NotBlank(message = "calledNumber is mandatory")
  private String calledNumber;

  @JsonProperty("engagementDialogId")
  @NotBlank(message = "engagementDialogId is mandatory")
  private String engagementDialogId;

  @JsonProperty("participant")
  @Valid @NotNull (message = "participant is mandatory")
  private Participant participant;

  @JsonProperty("timestamp")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @NotNull(message = "timestamp is mandatory")
  private OffsetDateTime timestamp;

  public Call id(String id) {
    this.id = id;
    return this;
  }

  /**
   * call id
   * @return id
  */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Call callerNumber(String callerNumber) {
    this.callerNumber = callerNumber;
    return this;
  }

  /**
   * phone number of the caller
   * @return callerNumber
  */
  public String getCallerNumber() {
    return callerNumber;
  }

  public void setCallerNumber(String callerNumber) {
    this.callerNumber = callerNumber;
  }

  public Call calledNumber(String calledNumber) {
    this.calledNumber = calledNumber;
    return this;
  }

  /**
   * dialed phone number
   * @return calledNumber
  */
  public String getCalledNumber() {
    return calledNumber;
  }

  public void setCalledNumber(String calledNumber) {
    this.calledNumber = calledNumber;
  }

  public Call engagementDialogId(String engagementDialogId) {
    this.engagementDialogId = engagementDialogId;
    return this;
  }

  /**
   * A Media Platform specific identifier for the engagement dialog on which to initialize
   * @return engagementDialogId
  */
  
  public String getEngagementDialogId() {
    return engagementDialogId;
  }

  public void setEngagementDialogId(String engagementDialogId) {
    this.engagementDialogId = engagementDialogId;
  }

  public Call participant(Participant participant) {
    this.participant = participant;
    return this;
  }

  /**
   * Get participant
   * @return participant
  */
  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }

  public Call timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * timestamp
   * @return timestamp
  */
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Call call = (Call) o;
    return Objects.equals(this.id, call.id) &&
        Objects.equals(this.callerNumber, call.callerNumber) &&
        Objects.equals(this.calledNumber, call.calledNumber) &&
        Objects.equals(this.engagementDialogId, call.engagementDialogId) &&
        Objects.equals(this.participant, call.participant) &&
        Objects.equals(this.timestamp, call.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, callerNumber, calledNumber, engagementDialogId, participant, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Call {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    callerNumber: ").append(toIndentedString(callerNumber)).append("\n");
    sb.append("    calledNumber: ").append(toIndentedString(calledNumber)).append("\n");
    sb.append("    engagementDialogId: ").append(toIndentedString(engagementDialogId)).append("\n");
    sb.append("    participant: ").append(toIndentedString(participant)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

