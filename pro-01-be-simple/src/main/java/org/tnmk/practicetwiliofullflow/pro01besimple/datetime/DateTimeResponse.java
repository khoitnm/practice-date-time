package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import java.time.OffsetDateTime;

public class DateTimeResponse {
  private OffsetDateTime dateA;
  private OffsetDateTime dateB;

  public DateTimeResponse(OffsetDateTime dateA, OffsetDateTime dateB) {
    this.dateA = dateA;
    this.dateB = dateB;
  }

  public OffsetDateTime getDateA() {
    return dateA;
  }

  public OffsetDateTime getDateB() {
    return dateB;
  }
}
