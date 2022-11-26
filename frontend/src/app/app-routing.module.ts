import { NgModule } from '@angular/core';
import { ProducersComponent } from "./producers/producers.component";
import { RouterModule, Routes } from "@angular/router";
import { ProducerDetailComponent } from "./producer-detail/producer-detail.component";

const routes: Routes = [
  { path: 'producers', component: ProducersComponent },
  { path: '', redirectTo: '/producers', pathMatch: 'full' },
  { path: 'producer', component: ProducerDetailComponent },
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
