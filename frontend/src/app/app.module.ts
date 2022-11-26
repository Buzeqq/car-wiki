import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProducersComponent } from './producers/producers.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatListModule } from "@angular/material/list";
import { MatCardModule } from "@angular/material/card";
import { MatButtonModule } from "@angular/material/button";
import { ProducerDetailComponent } from './producer-detail/producer-detail.component';
import { MatDialogModule } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { ProducerDetailDeleteDialogComponent } from './producer-detail-delete-dialog/producer-detail-delete-dialog.component';
import { ProducerCreateFormDialogComponent } from './producer-create-form-dialog/producer-create-form-dialog.component';
import { CarsComponent } from './cars/cars.component';
import {MatRippleModule} from "@angular/material/core";
import { CarDetailComponent } from './car-detail/car-detail.component';
import { CarDetailDeleteDialogComponent } from './car-detail-delete-dialog/car-detail-delete-dialog.component';
import { CarCreateFormDialogComponent } from './car-create-form-dialog/car-create-form-dialog.component';
import {MatSelectModule} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    ProducersComponent,
    ProducerDetailComponent,
    ProducerDetailDeleteDialogComponent,
    ProducerCreateFormDialogComponent,
    CarsComponent,
    CarDetailComponent,
    CarDetailDeleteDialogComponent,
    CarCreateFormDialogComponent,
  ],
    imports: [
        BrowserModule,
        FormsModule,
        BrowserAnimationsModule,
        MatCardModule,
        MatButtonModule,
        MatDialogModule,
        MatFormFieldModule,
        MatInputModule,
        AppRoutingModule,
        HttpClientModule,
        MatProgressSpinnerModule,
        ReactiveFormsModule,
        MatListModule,
        MatRippleModule,
        MatSelectModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
