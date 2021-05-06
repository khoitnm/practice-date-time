import {DateTimeEntity} from "./DateTimeEntity";
import beAxios from "./common/be-axios/beAxios";

const dateTimeBeService = {
  now: async (): Promise<DateTimeEntity> =>  {
    const axiosResponse = await beAxios.get<DateTimeEntity>(`/now`);
    const rawResult = axiosResponse.data;
    rawResult.dateDate = new Date(rawResult.date);
    rawResult.dateInUTCDate = new Date(rawResult.dateInUTC);
    rawResult.offsetDateTimeDate = new Date(rawResult.offsetDateTime);
    rawResult.offsetDateTimeInUTCDate = new Date(rawResult.offsetDateTimeInUTC);
    rawResult.zonedDateTimeDate = new Date(rawResult.zonedDateTime);
    rawResult.zonedDateTimeInUTCDate = new Date(rawResult.zonedDateTimeInUTC);
    return rawResult;
  }
}
export default dateTimeBeService;