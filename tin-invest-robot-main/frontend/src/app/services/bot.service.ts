import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bot} from "../interfaces/bot";
import {environment} from 'src/environments/environment';
import {BotChart} from "../interfaces/botChart";
import {Statistics} from "../interfaces/statistics";
import { BotConfig } from '../interfaces/bot-config';

@Injectable({
  providedIn: 'root'
})
export class BotService {
  private readonly BASE_URL = environment.BASE_URL;

  constructor(private http: HttpClient) {
  }

  getBots(): Observable<Bot[]> {
    console.log(this.BASE_URL);
    return this.http.get<Bot[]>(`${this.BASE_URL}/bot`)
  }



  createRealBot(config: BotConfig): Observable<Bot> {
    return this.http.post<Bot>(`${this.BASE_URL}/bot`, config);
  }

  /** Создать песочницу */
  createSandboxBot(config: BotConfig): Observable<Bot> {
    return this.http.post<Bot>(`${this.BASE_URL}/bot/sandbox`, config);
  }

  /** Создать симуляционного бота */
  createSimulationBot(config: BotConfig, start: string): Observable<Bot> {
    // передаём стартовую дату в query-параметре
    return this.http.post<Bot>(
      `${this.BASE_URL}/bot/simulation?start=${encodeURIComponent(start)}`,
      config
    );
  }

  /** Удалить бота */
  removeBot(id: string): Observable<void> {
    return this.http.delete<void>(`${this.BASE_URL}/bot/${id}`);
  }









  getBotChart(botUuid: string, offset: number): Observable<BotChart> {
    let params = new HttpParams();

    // Begin assigning parameters

    params = params.append('offset', offset + '');
    return this.http.get<BotChart>(`${this.BASE_URL}/indicator/bot/${botUuid}`, {params})
  }

  getStatistic(botUuid: string): Observable<Statistics> {
    return this.http.get<Statistics>(`${this.BASE_URL}/statistic/bot/${botUuid}`)
  }
}
