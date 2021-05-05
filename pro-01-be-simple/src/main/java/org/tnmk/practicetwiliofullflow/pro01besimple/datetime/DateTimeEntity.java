package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "tbl_date_time")
public class DateTimeEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "datetime") //by default, it would use datetime2(7), but the performance will be slower.
  private OffsetDateTime nowOffsetDateTime;

  @Column(columnDefinition = "datetime")
  private ZonedDateTime nowZonedDateTime;

  @Column(columnDefinition = "datetime")
  private OffsetDateTime nowOffsetDateTimeInUTC;

  @Column(columnDefinition = "datetime")
  private ZonedDateTime nowZonedDateTimeInUTC;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OffsetDateTime getNowOffsetDateTime() {
    return nowOffsetDateTime;
  }

  public void setNowOffsetDateTime(OffsetDateTime nowOffsetDateTime) {
    this.nowOffsetDateTime = nowOffsetDateTime;
  }

  public ZonedDateTime getNowZonedDateTime() {
    return nowZonedDateTime;
  }

  public void setNowZonedDateTime(ZonedDateTime nowZonedDateTime) {
    this.nowZonedDateTime = nowZonedDateTime;
  }

  public OffsetDateTime getNowOffsetDateTimeInUTC() {
    return nowOffsetDateTimeInUTC;
  }

  public void setNowOffsetDateTimeInUTC(OffsetDateTime nowOffsetDateTimeInUTC) {
    this.nowOffsetDateTimeInUTC = nowOffsetDateTimeInUTC;
  }

  public ZonedDateTime getNowZonedDateTimeInUTC() {
    return nowZonedDateTimeInUTC;
  }

  public void setNowZonedDateTimeInUTC(ZonedDateTime nowZonedDateTimeInUTC) {
    this.nowZonedDateTimeInUTC = nowZonedDateTimeInUTC;
  }
}
