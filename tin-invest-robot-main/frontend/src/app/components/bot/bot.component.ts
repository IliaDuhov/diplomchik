import { Component, OnDestroy, OnInit } from '@angular/core';
import {BotService} from "../../services/bot.service";
import {Bot} from "../../interfaces/bot";
import {Subject} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {takeUntil} from "rxjs/operators";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; 
import { Clipboard } from '@angular/cdk/clipboard';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-bot',
  templateUrl: './bot.component.html',
  styleUrls: ['./bot.component.scss']
})
export class BotComponent implements OnInit, OnDestroy {
  bots: Bot[] = [];

  botForm: FormGroup;
  strategyOptions = ['ONE_MINUTE_SCALPING', 'THREE_LINE_STRIKE'];

  figiOptions = [
    { figi: 'BBG004730N88', name: 'Sberbank' },
    { figi: 'BBG004730ZJ9', name: 'VTB bank' },
    { figi: 'BBG00475KHX6', name: 'TransOil' },
    { figi: 'BBG004731032', name: 'Lukoil' },
    { figi: 'BBG004S68507', name: 'MAGN' },
    { figi: 'BBG004731354', name: 'RosOil' },
    { figi: 'BBG004730RP0', name: 'Gazprom' }
  ];
  
  
  
  
  
  private destroy$ = new Subject<void>();


  constructor(
    private service: BotService,
    private fb: FormBuilder,
    private router: Router,
    private clipboard: Clipboard,
    private snack: MatSnackBar
  ) {
    this.botForm = this.fb.group({
      figi: ['', Validators.required],
      takeProfit: [
        0.0008,
        [
          Validators.required,
          Validators.min(0.0001),
          Validators.max(0.7)
        ]
      ],
      stopLoss: [
        0.0003,
        [
          Validators.required,
          Validators.min(0.0001),
          Validators.max(0.7)
        ]
      ],
      numberOfLots: [1, [Validators.required, Validators.min(1)]],
      strategyCode: [this.strategyOptions[0], Validators.required],
      telegramBotChatId: [''],
      token: ['1111111111111111111111111111111111111111111111']
    });
    
  }

  ngOnInit(): void {
    this.service.getBots().subscribe((bots: Bot[]) => {
      this.bots = bots;
    })
  }

  

  loadBots(): void {
    this.service.getBots()
      .pipe(takeUntil(this.destroy$))
      .subscribe(bots => this.bots = bots);
  }

  copyFigi(figi: string) {
    this.botForm.get('figi')!.setValue(figi);
    this.clipboard.copy(figi);
    this.snack.open(`FIGI ${figi} скопирован`, 'OK', { duration: 2000 });
  }

  selectFigi(f: string) {
    this.botForm.get('figi')?.setValue(f);
    navigator.clipboard.writeText(f).catch(() => {});
  }


  createReal(): void {
    if (this.botForm.invalid) return;
    this.service.createRealBot(this.botForm.value)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.loadBots());
  }

  createSandbox(): void {
    if (this.botForm.invalid) return;
    this.service.createSandboxBot(this.botForm.value)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.loadBots());
  }

  createSimulation(): void {
    if (this.botForm.invalid) return;
    const start = new Date().toISOString();
    this.service.createSimulationBot(this.botForm.value, start)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.loadBots());
  }
  
  removeBot(id: string): void {
    this.service.removeBot(id)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.loadBots());
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }


}
