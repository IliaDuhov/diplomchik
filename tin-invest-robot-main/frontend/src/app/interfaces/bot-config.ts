import { BotEnvType } from '../types/botEnv.type';

export interface BotConfig {
  figi: string;
  takeProfit: number;
  stopLoss: number;
  accountId?: string;
  numberOfLots: number;
  strategyCode: string;     
  telegramBotChatId?: string;
  strategiesConfig?: any; 
}
