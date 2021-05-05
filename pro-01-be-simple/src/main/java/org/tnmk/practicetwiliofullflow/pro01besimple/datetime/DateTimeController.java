package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class DateTimeController {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  private static final int sixMonthsInSec = 6 * 30 * 24 * 60 * 60;

  private final DateTimeRepository dateTimeRepository;

  public DateTimeController(DateTimeRepository dateTimeRepository) {
    this.dateTimeRepository = dateTimeRepository;
  }

  @GetMapping("/date-time/system-default-zone")
  public DateTimeResponse generateDateTimFromSystemDefaultZoneId() {
    LocalDateTime localDateTime = generateLocalDateTime();
    ZonedDateTime beginZonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    ZonedDateTime endZonedDateTime = beginZonedDateTime.plus(sixMonthsInSec, ChronoUnit.SECONDS);

    ZonedDateTimePair zoneIdSystemDefault_zonedDateTimePair = new ZonedDateTimePair(beginZonedDateTime, endZonedDateTime);
    DateTimeHelper.logZonedDateTimePair("ZoneIdSystemDefault", zoneIdSystemDefault_zonedDateTimePair);
    DateTimeHelper.toOffsetDateTimePair("ZoneIdSystemDefault", zoneIdSystemDefault_zonedDateTimePair);

    logger.info("ZoneIdUTC ------------------------------------------------------------------------------------------");
    ZonedDateTimePair zoneIdUTC_zonedDateTimePair = DateTimeHelper
        .toZoneDateTimePair("ZoneIdUTC", zoneIdSystemDefault_zonedDateTimePair, ZoneId.of("UTC"));
    DateTimeHelper.toOffsetDateTimePair("ZoneIdUTC", zoneIdUTC_zonedDateTimePair);

    logger.info("ZoneOffsetUTC ------------------------------------------------------------------------------------------");
    ZonedDateTimePair zoneOffsetUTC_zonedDateTimePair = DateTimeHelper
        .toZoneDateTimePair("ZoneOffsetUTC", zoneIdSystemDefault_zonedDateTimePair, ZoneOffset.UTC);
    OffsetDateTimePair zoneOffsetUTC_offsetDateTimePair = DateTimeHelper.toOffsetDateTimePair("ZoneOffsetUTC", zoneOffsetUTC_zonedDateTimePair);

    return new DateTimeResponse(zoneOffsetUTC_offsetDateTimePair.begin, zoneOffsetUTC_offsetDateTimePair.end);
  }

  @GetMapping("/local-date-time")
  public DateTimeResponse generateDateTime_fromUTC() {
    LocalDateTime localDateTime = generateLocalDateTime();
    generateZonedDateTimePair(ZoneId.of("UTC"), localDateTime);
    generateZonedDateTimePair(ZoneOffset.UTC, localDateTime);
    generateOffsetDateTimePair(ZoneOffset.UTC, localDateTime);

    generateZonedDateTimePair(ZoneId.systemDefault(), localDateTime);
    generateOffsetDateTimePair(ZoneId.systemDefault().getRules().getOffset(localDateTime), localDateTime);

    return null;
  }

  @GetMapping("/now")
  public List<DateTimeEntity> nowUTC() {
    OffsetDateTime nowOffsetDateTime = OffsetDateTime.now();
    ZonedDateTime nowZonedDateTime = ZonedDateTime.now();
    OffsetDateTime offsetDateTimeFromZonedDateTime = nowZonedDateTime.toOffsetDateTime();

    OffsetDateTime nowOffsetDateTimeInUTC = nowOffsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);
    ZonedDateTime nowZonedDateTimeInUTC = nowZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    OffsetDateTime offsetDateTime_from_nowZonedDateTimeInUTC = nowZonedDateTime.toOffsetDateTime();

    logger.info(""
            + "\nnowOffsetDateTime: {}."
            + "\nnowZonedDateTime : {}"
            + "\n   offset of it  : {}"
            + "\n--------------------------"
            + "\nnowOffsetDateTimeInUTC: {}"
            + "\nnowZonedDateTimeInUTC : {}"
            + "\n   offset of it       : {}",
        nowOffsetDateTime, nowZonedDateTime, offsetDateTimeFromZonedDateTime,
        nowOffsetDateTimeInUTC, nowZonedDateTimeInUTC, offsetDateTime_from_nowZonedDateTimeInUTC);

    DateTimeEntity dateTimeEntity = new DateTimeEntity();
    dateTimeEntity.setNowOffsetDateTime(nowOffsetDateTime);
    dateTimeEntity.setNowZonedDateTime(nowZonedDateTime);
    dateTimeEntity.setNowOffsetDateTimeInUTC(nowOffsetDateTimeInUTC);
    dateTimeEntity.setNowZonedDateTimeInUTC(nowZonedDateTimeInUTC);
    DateTimeEntity savedEntity = dateTimeRepository.save(dateTimeEntity);

    List<DateTimeEntity> allEntities = dateTimeRepository.findAll();
    return allEntities;
  }

  @GetMapping("/clock")
  public DateTimeResponse generateDateTime_fromClock() {
    Instant instant = Instant.parse("2021-05-31T23:23:23Z");
    Clock clock = Clock.fixed(instant, ZoneId.systemDefault());
    generateOffsetDateTimePair(clock);

    return null;
  }

  private ZonedDateTimePair generateZonedDateTimePair(ZoneId zoneId, LocalDateTime localDateTime) {
    ZonedDateTime begin = ZonedDateTime.of(localDateTime, zoneId);
    ZonedDateTime end = begin.plusSeconds(sixMonthsInSec);//begin.plusMonths(6);//
    ZonedDateTimePair pair = new ZonedDateTimePair(begin, end);
    DateTimeHelper.logZonedDateTimePair(zoneId.toString(), pair);
    DateTimeHelper.toOffsetDateTimePair(zoneId.toString(), pair);
    return pair;
  }

  private OffsetDateTimePair generateOffsetDateTimePair(Clock clock) {
    OffsetDateTime begin = OffsetDateTime.now(clock);
    OffsetDateTime end = begin.plusSeconds(sixMonthsInSec);//begin.plusMonths(6);//
    OffsetDateTimePair pair = new OffsetDateTimePair(begin, end);
    DateTimeHelper.logOffsetDateTimePair(clock.getZone().toString(), pair);

    ZonedDateTime beginZonedDateTime = ZonedDateTime.now(clock);
    ZonedDateTime endZonedDateTime = beginZonedDateTime.plusMonths(6);//begin.plusSeconds(sixMonthsInSec);
    ZonedDateTimePair pairZonedDateTime = new ZonedDateTimePair(beginZonedDateTime, endZonedDateTime);
    DateTimeHelper.logZonedDateTimePair(clock.getZone().toString(), pairZonedDateTime);
    return pair;
  }

  private OffsetDateTimePair generateOffsetDateTimePair(ZoneOffset zoneOffset, LocalDateTime localDateTime) {
    OffsetDateTime begin = OffsetDateTime.of(localDateTime, zoneOffset);
    OffsetDateTime end = begin.plusSeconds(sixMonthsInSec);//begin.plusMonths(6);//
    OffsetDateTimePair pair = new OffsetDateTimePair(begin, end);
    DateTimeHelper.logOffsetDateTimePair(zoneOffset.toString(), pair);
    return pair;
  }

  private LocalDateTime generateLocalDateTime() {
    int year = 2021;
    int month = 5;
    int day = 31;
    int hour = 23;
    int min = 23;
    int sec = 23;
    int nano = 0;
    return LocalDateTime.of(year, month, day, hour, min, sec, nano);
  }

}
