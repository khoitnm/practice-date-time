package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import java.time.ZonedDateTime;

class ZonedDateTimePair {
  protected ZonedDateTime begin;
  protected ZonedDateTime end;

  public ZonedDateTimePair(ZonedDateTime begin, ZonedDateTime end) {
    this.begin = begin;
    this.end = end;
  }
}
