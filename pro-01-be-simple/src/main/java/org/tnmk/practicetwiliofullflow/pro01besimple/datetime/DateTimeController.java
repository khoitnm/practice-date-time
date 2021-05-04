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
    int month = 5;
    int day = 31;
    int hour = 23;
    int min = 23;
    int sec = 23;
    int nano = 0;
    //    LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour,min, sec);
    ZonedDateTime beginZonedDateTime = ZonedDateTime.of(year, month, day, hour, min, sec, nano, ZoneId.systemDefault());
    int sixMonthsInSec = 6 * 30 * 24 * 60 * 60;
    ZonedDateTime endZonedDateTime = beginZonedDateTime.plus(sixMonthsInSec, ChronoUnit.SECONDS);
    logger.info("ZoneIdSystemDefault -\t ZonedDateTime:\t {} - {}",
        String.format("%-47s", beginZonedDateTime), endZonedDateTime);

    logger.info("ZoneIdUTC ------------------------------------------------------------------------------------------");
    ZonedDateTime beginZonedDateTimeZoneIdUTC = beginZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    ZonedDateTime endZonedDateTimeZoneIdUTC = endZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    logger.info("ZoneIdUTC -\t\t\t ZonedDateTime:\t {} - {}",
        String.format("%-47s", beginZonedDateTimeZoneIdUTC), endZonedDateTimeZoneIdUTC);

    OffsetDateTime beginOffsetDateTimeZoneIdUTC = beginZonedDateTimeZoneIdUTC.toOffsetDateTime();
    OffsetDateTime endOffsetDateTimeZoneIdUTC = endZonedDateTimeZoneIdUTC.toOffsetDateTime();
    logger.info("ZoneIdUTC -\t\t\t OffsetDateTime: {} - {}",
        String.format("%-47s", beginOffsetDateTimeZoneIdUTC), endOffsetDateTimeZoneIdUTC);

    logger.info("ZoneOffsetUTC ------------------------------------------------------------------------------------------");
    ZonedDateTime beginZonedDateTimeZoneOffsetUTC = beginZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    ZonedDateTime endZonedDateTimeZoneOffsetUTC = endZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    logger.info("ZoneOffsetUTC -\t\t ZonedDateTime:\t {} - {}",
        String.format("%-47s", beginZonedDateTimeZoneOffsetUTC), endZonedDateTimeZoneOffsetUTC);

    OffsetDateTime beginOffsetDateTimeZoneOffsetUTC = beginZonedDateTimeZoneOffsetUTC.toOffsetDateTime();
    OffsetDateTime endOffsetDateTimeZoneOffsetUTC = endZonedDateTimeZoneOffsetUTC.toOffsetDateTime();
    logger.info("ZoneOffsetUTC -\t\t OffsetDateTime: {} - {}",
        String.format("%-47s", beginOffsetDateTimeZoneOffsetUTC), endOffsetDateTimeZoneOffsetUTC);

    return new DateTimeResponse(beginOffsetDateTimeZoneOffsetUTC, endOffsetDateTimeZoneOffsetUTC);
  }
}
