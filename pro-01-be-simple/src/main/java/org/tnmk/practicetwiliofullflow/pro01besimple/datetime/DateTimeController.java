package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@RestController
public class DateTimeController {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @GetMapping("/date-time")
  public DateTimeResponse generateDateTime() {
    int year = 2021;
    int month = 12;
    int day = 31;
    int hour = 23;
    int min = 23;
    int sec = 23;
    int nano = 999999999;
    //    LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour,min, sec);
    ZonedDateTime beginZonedDateTime = ZonedDateTime.of(year, month, day, hour, min, sec, nano, ZoneId.systemDefault());

    int sixMonthsInSec = 6 * 30 * 24 * 60 * 60;
    ZonedDateTime endZonedDateTime = beginZonedDateTime.plus(sixMonthsInSec, ChronoUnit.SECONDS);

    logger.info("ZonedDateTime - ZoneIdSystemDefault: Begin: {} and Six months later: {}",
        beginZonedDateTime, endZonedDateTime);

    ZonedDateTime beginZonedDateTimeZoneIdUTC = beginZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    ZonedDateTime endZonedDateTimeZoneIdUTC = endZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    logger.info("ZonedDateTime - ZoneIdUTC: Begin: {} and Six months later: {}",
        beginZonedDateTimeZoneIdUTC, endZonedDateTimeZoneIdUTC);

    OffsetDateTime beginOffsetDateTimeZoneIdUTC = beginZonedDateTimeZoneIdUTC.toOffsetDateTime();
    OffsetDateTime endOffsetDateTimeZoneIdUTC = endZonedDateTimeZoneIdUTC.toOffsetDateTime();
    logger.info("OffsetDateTime - ZoneIdUTC: Begin: {} and Six months later: {}",
        beginOffsetDateTimeZoneIdUTC, endOffsetDateTimeZoneIdUTC);

    ZonedDateTime beginZonedDateTimeZoneOffsetUTC = beginZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    ZonedDateTime endZonedDateTimeZoneOffsetUTC = endZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    logger.info("ZonedDateTime - ZoneOffsetUTC: Begin: {} and Six months later: {}",
        beginZonedDateTimeZoneOffsetUTC, endZonedDateTimeZoneOffsetUTC);

    OffsetDateTime beginOffsetDateTimeZoneOffsetUTC = beginZonedDateTimeZoneOffsetUTC.toOffsetDateTime();
    OffsetDateTime endOffsetDateTimeZoneOffsetUTC = endZonedDateTimeZoneOffsetUTC.toOffsetDateTime();
    logger.info("OffsetDateTime - ZoneOffsetUTC: Begin: {} and Six months later: {}",
        beginOffsetDateTimeZoneOffsetUTC, endOffsetDateTimeZoneOffsetUTC);

    return new DateTimeResponse(beginOffsetDateTimeZoneOffsetUTC, endOffsetDateTimeZoneOffsetUTC);
  }
}
