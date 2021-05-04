package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeHelper {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  static ZonedDateTimePair toZoneDateTimePair(String msgZoneTitle, ZonedDateTimePair pair, ZoneId zoneId) {
    ZonedDateTime begin = pair.begin.withZoneSameInstant(zoneId);
    ZonedDateTime end = pair.end.withZoneSameInstant(zoneId);
    ZonedDateTimePair resultPair = new ZonedDateTimePair(begin, end);
    logZonedDateTimePair(msgZoneTitle, resultPair);
    return resultPair;
  }

  static OffsetDateTimePair toOffsetDateTimePair(String msgZoneTitle, ZonedDateTimePair pair) {
    OffsetDateTime begin = pair.begin.toOffsetDateTime();
    OffsetDateTime end = pair.end.toOffsetDateTime();
    OffsetDateTimePair resultPair = new OffsetDateTimePair(begin, end);
    logOffsetDateTimePair(msgZoneTitle, resultPair);
    return resultPair;
  }

  static void logZonedDateTimePair(String msgZoneTitle, ZonedDateTimePair pair) {
    logger.info("{} - ZonedDateTime:  {} - {}",
        String.format("%-20s", msgZoneTitle), String.format("%-47s", pair.begin), pair.end);
  }

  static void logOffsetDateTimePair(String msgZoneTitle, OffsetDateTimePair pair) {
    logger.info("{} - OffsetDateTime: {} - {}",
        String.format("%-20s", msgZoneTitle), String.format("%-47s", pair.begin), pair.end);
  }
}
