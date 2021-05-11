package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

public class DateTimeResult {
  private final DateTimeEntity now;
  private final DateTimeEntity sixMonths;

  public DateTimeResult(DateTimeEntity now, DateTimeEntity sixMonths) {
    this.now = now;
    this.sixMonths = sixMonths;
  }

  public DateTimeEntity getNow() {
    return now;
  }

  public DateTimeEntity getSixMonths() {
    return sixMonths;
  }
}
