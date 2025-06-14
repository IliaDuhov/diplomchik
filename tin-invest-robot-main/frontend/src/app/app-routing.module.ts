import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BotComponent} from "./components/bot/bot.component";
import {StatisticsComponent} from "./components/statistics/statistics.component";
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: { animation: 'HomePage' }
  },
  {
    path: 'bots',
    component: BotComponent,
    data: { animation: 'BotsPage' }
  },
  {
    path: 'bot/:botId',
    component: StatisticsComponent,
    data: { animation: 'StatsPage' }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: { animation: 'RegisterPage' }
  },
  { path: '**', redirectTo: '' }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
