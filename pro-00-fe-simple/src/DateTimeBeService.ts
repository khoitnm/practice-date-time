import {DateTimeEntity} from "./DateTimeEntity";
import beAxios from "./common/be-axios/beAxios";
import DateTimeResult from "./DateTimeResult";

const convertDateTime =(dateTimeEntity: DateTimeEntity): void => {
  dateTimeEntity.dateDate = new Date(dateTimeEntity.date);
  dateTimeEntity.dateInUTCDate = new Date(dateTimeEntity.dateInUTC);
  dateTimeEntity.offsetDateTimeDate = new Date(dateTimeEntity.offsetDateTime);
  dateTimeEntity.offsetDateTimeInUTCDate = new Date(dateTimeEntity.offsetDateTimeInUTC);
  dateTimeEntity.zonedDateTimeDate = new Date(dateTimeEntity.zonedDateTime);
  dateTimeEntity.zonedDateTimeInUTCDate = new Date(dateTimeEntity.zonedDateTimeInUTC);
}

const dateTimeBeService = {
  now: async (): Promise<DateTimeResult> =>  {
    const axiosResponse = await beAxios.get<DateTimeResult>(`/now`);
    const rawResult = axiosResponse.data;
    convertDateTime(rawResult.now);
    convertDateTime(rawResult.sixMonths);
    return rawResult;
  }


}
export default dateTimeBeService;