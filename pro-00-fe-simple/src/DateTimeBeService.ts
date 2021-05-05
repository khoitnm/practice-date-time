import {DateTimeEntity} from "./DateTimeEntity";
import beAxios from "./common/be-axios/beAxios";

const dateTimeBeService = {
  now: async (): Promise<Array<DateTimeEntity>> =>  {
    const axiosResponse = await beAxios.get(`/now`);
    const result = axiosResponse.data;
    return result;
  }
}
export default dateTimeBeService;