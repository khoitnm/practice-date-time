package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_date_time")
public class DateTimeEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "datetime") //by default, it would use datetime2(7), but the performance will be slower.
  private OffsetDateTime offsetDateTime;

  @Column(columnDefinition = "datetime")
  private ZonedDateTime zonedDateTime;

  @Column(columnDefinition = "datetime")
  private OffsetDateTime offsetDateTimeInUTC;

  @Column(columnDefinition = "datetime")
  private ZonedDateTime zonedDateTimeInUTC;

  @Column(columnDefinition = "datetime")
  private Date date;

  @Column(columnDefinition = "datetime")
  private Date dateInUTC;

  @Override public String toString() {
    return "DateTimeEntity{" +
        "id=" + id +
        ", offsetDateTime=" + offsetDateTime +
        ", zonedDateTime=" + zonedDateTime +
        ", offsetDateTimeInUTC=" + offsetDateTimeInUTC +
        ", zonedDateTimeInUTC=" + zonedDateTimeInUTC +
        ", date=" + date +
        ", dateInUTC=" + dateInUTC +
        '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OffsetDateTime getOffsetDateTime() {
    return offsetDateTime;
  }

  public void setOffsetDateTime(OffsetDateTime nowOffsetDateTime) {
    this.offsetDateTime = nowOffsetDateTime;
  }

  public ZonedDateTime getZonedDateTime() {
    return zonedDateTime;
  }

  public void setZonedDateTime(ZonedDateTime nowZonedDateTime) {
    this.zonedDateTime = nowZonedDateTime;
  }

  public OffsetDateTime getOffsetDateTimeInUTC() {
    return offsetDateTimeInUTC;
  }

  public void setOffsetDateTimeInUTC(OffsetDateTime nowOffsetDateTimeInUTC) {
    this.offsetDateTimeInUTC = nowOffsetDateTimeInUTC;
  }

  public ZonedDateTime getZonedDateTimeInUTC() {
    return zonedDateTimeInUTC;
  }

  public void setZonedDateTimeInUTC(ZonedDateTime nowZonedDateTimeInUTC) {
    this.zonedDateTimeInUTC = nowZonedDateTimeInUTC;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getDateInUTC() {
    return dateInUTC;
  }

  public void setDateInUTC(Date dateInUTC) {
    this.dateInUTC = dateInUTC;
  }
}
