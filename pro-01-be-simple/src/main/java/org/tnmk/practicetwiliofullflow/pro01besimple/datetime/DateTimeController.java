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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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
  public DateTimeResult nowUTC() {
    OffsetDateTime nowOffsetDateTime = OffsetDateTime.now();
    ZonedDateTime nowZonedDateTime = ZonedDateTime.now();
    DateTimeEntity nowDateTimeEntity = reportDateTime(nowOffsetDateTime, nowZonedDateTime);

//    OffsetDateTime sixMonths_OffsetDateTime = nowOffsetDateTime.plusSeconds(sixMonthsInSec);
//    ZonedDateTime sixMonths_ZonedDateTime = nowZonedDateTime.plusSeconds(sixMonthsInSec);

    OffsetDateTime sixMonths_OffsetDateTime = nowOffsetDateTime.plusMonths(6);
    ZonedDateTime sixMonths_ZonedDateTime = nowZonedDateTime.plusMonths(6);
    DateTimeEntity sixMonths_DateTimeEntity = reportDateTime(sixMonths_OffsetDateTime, sixMonths_ZonedDateTime);

    DateTimeEntity loadedNow = saveIntoDBAndReload(nowDateTimeEntity);
    DateTimeEntity loadedSixMonths = saveIntoDBAndReload(sixMonths_DateTimeEntity);
    return new DateTimeResult(loadedNow, loadedSixMonths);
  }

  private DateTimeEntity reportDateTime(OffsetDateTime offsetDateTime, ZonedDateTime zonedDateTime){
    OffsetDateTime zonedDateTimeToOffsetDateTime = zonedDateTime.toOffsetDateTime();
    OffsetDateTime offsetDateTime_from_ZonedDateTime = OffsetDateTime.of(zonedDateTime.toLocalDateTime(), zonedDateTime.getOffset());

    OffsetDateTime offsetDateTimeInUTC = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);
    ZonedDateTime zonedDateTimeInUTC = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    OffsetDateTime zonedDateTimeInUTCToOffsetDateTime = zonedDateTime.toOffsetDateTime();
    OffsetDateTime offsetDateTime_from_ZonedDateTimeInUTC = OffsetDateTime.of(zonedDateTimeInUTC.toLocalDateTime(), ZoneOffset.UTC);

    logger.info(""
            + "\noffsetDateTime     : {}."
            + "\nzonedDateTime      : {}"
            + "\n   toOffsetDateTime: {}"
            + "\n   offsetDateTime  : {}"
            + "\n--------------------------"
            + "\noffsetDateTimeInUTC: {}"
            + "\nzonedDateTimeInUTC : {}"
            + "\n   toOffsetDateTime: {}"
            + "\n   offsetDateTime  : {}"
        ,
        offsetDateTime, zonedDateTime, zonedDateTimeToOffsetDateTime, offsetDateTime_from_ZonedDateTime,
        offsetDateTimeInUTC, zonedDateTimeInUTC, zonedDateTimeInUTCToOffsetDateTime, offsetDateTime_from_ZonedDateTimeInUTC);

    DateTimeEntity dateTimeEntity = constructDateTimeEntity(offsetDateTime, zonedDateTime, zonedDateTimeToOffsetDateTime,
        offsetDateTimeInUTC, zonedDateTimeInUTC, zonedDateTimeInUTCToOffsetDateTime);
    return dateTimeEntity;
  }

  private DateTimeEntity constructDateTimeEntity(
      OffsetDateTime nowOffsetDateTime, ZonedDateTime nowZonedDateTime, OffsetDateTime nowZonedDateTimeToOffsetDateTime,
      OffsetDateTime nowOffsetDateTimeInUTC, ZonedDateTime nowZonedDateTimeInUTC, OffsetDateTime nowZonedDateTimeInUTCToOffsetDateTime) {

    DateTimeEntity dateTimeEntity = new DateTimeEntity();
    dateTimeEntity.setOffsetDateTime(nowOffsetDateTime);
    dateTimeEntity.setZonedDateTime(nowZonedDateTime);
    dateTimeEntity.setZonedDateTimeToOffsetDateTime(nowZonedDateTimeToOffsetDateTime);
    dateTimeEntity.setOffsetDateTimeInUTC(nowOffsetDateTimeInUTC);
    dateTimeEntity.setZonedDateTimeInUTC(nowZonedDateTimeInUTC);
    dateTimeEntity.setZonedDateTimeInUTCToOffsetDateTime(nowZonedDateTimeInUTCToOffsetDateTime);

    //    TimeZone.setDefault(TimeZone.getDefault());
    logger.info("Default TimeZone: {}", TimeZone.getDefault());
    dateTimeEntity.setDate(new Date());
    dateTimeEntity.setDateInUTC(Date.from(Instant.now()));
    return dateTimeEntity;
  }

  private DateTimeEntity saveIntoDBAndReload(DateTimeEntity dateTimeEntity) {
    DateTimeEntity savedEntity = dateTimeRepository.save(dateTimeEntity);

    List<DateTimeEntity> allEntities = dateTimeRepository.findAll();
    Optional<DateTimeEntity> dataFromDB = allEntities.stream()
        .filter(entity -> entity.getId().equals(savedEntity.getId()))
        .findFirst();
    return dataFromDB.get();
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
