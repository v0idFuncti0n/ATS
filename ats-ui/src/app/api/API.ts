import {environment} from "../../environments/environment";

export class API {
  public static HOST: string = environment.host;

  public static AUTHENTICATE: string = API.HOST + "authenticate";

  public static BOOTCAMPS: string = API.HOST + "bootcamps";
  public static BOOTCAMP: string = API.HOST + "bootcamp";

  public static CANDIDATES: string = API.HOST + "candidates";

  public static TEST: string = API.HOST + "tests";

  public static TEST_INFO = API.HOST + "testInfo"
}
