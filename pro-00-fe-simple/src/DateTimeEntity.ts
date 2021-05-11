export interface DateTimeEntity{
  id:number;
  offsetDateTime: string;
  offsetDateTimeDate: Date;
  zonedDateTime: string;
  zonedDateTimeToOffsetDateTime: string;
  zonedDateTimeDate: Date;
  offsetDateTimeInUTC: string;
  offsetDateTimeInUTCDate: Date;
  zonedDateTimeInUTC: string;
  zonedDateTimeInUTCToOffsetDateTime: string;
  zonedDateTimeInUTCDate: Date;
  date: string;
  dateDate: Date;
  dateInUTC: string;
  dateInUTCDate: Date;
}