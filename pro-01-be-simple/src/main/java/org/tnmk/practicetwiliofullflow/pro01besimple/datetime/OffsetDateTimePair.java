package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import java.time.OffsetDateTime;

class OffsetDateTimePair {
  protected OffsetDateTime begin;
  protected OffsetDateTime end;

  public OffsetDateTimePair(OffsetDateTime begin, OffsetDateTime end) {
    this.begin = begin;
    this.end = end;
  }
}
