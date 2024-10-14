package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Operation outcome code
 */
public enum OutcomeCode {
  OK("OK"),
  KO("KO");

  private String value;

  OutcomeCode(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OutcomeCode fromValue(String text) {
    for (OutcomeCode b : OutcomeCode.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
