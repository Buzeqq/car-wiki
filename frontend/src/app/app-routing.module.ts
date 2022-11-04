import { NgModule } from '@angular/core';
import {ProducersComponent} from "./producers/producers.component";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ProducerDetailComponent} from "./producer-detail/producer-detail.component";

const routes: Routes = [
  { path: "producers", component: ProducersComponent },
  { path: "dashboard", component: DashboardComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'detail/:name', component: ProducerDetailComponent },
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
