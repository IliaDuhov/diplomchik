import { Component } from '@angular/core';
import {
  trigger,
  transition,
  style,
  animate,
  query,
  group
} from '@angular/animations';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  template: `
    <app-navbar></app-navbar>
    <div [@routeAnimations]="o && o.activatedRouteData['animation']">
      <router-outlet #o="outlet"></router-outlet>
    </div>
  `,
  animations: [
    trigger('routeAnimations', [
      transition('* <=> *', [
        query(':enter, :leave', style({ position: 'fixed', width: '100%' }), { optional: true }),
        group([
          query(':enter', [
            style({ opacity: 0, transform: 'translateY(20px)' }),
            animate('300ms ease-out', style({ opacity: 1, transform: 'translateY(0)' })),
          ], { optional: true }),
          query(':leave', [
            style({ opacity: 1 }),
            animate('300ms ease-out', style({ opacity: 0, transform: 'translateY(-20px)' })),
          ], { optional: true })
        ])
      ])
    ])
  ]
})
export class AppComponent {
  title = 'frontend';
  constructor(private router: Router) {
  this.router.navigate(['/register']);
}
showNavbar(): boolean {
    return this.router.url !== '/register';
  }
}
