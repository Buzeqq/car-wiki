import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProducersComponent } from './producers/producers.component';
import {FormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatListModule} from "@angular/material/list";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {ProducerDetailComponent, ProducerDetailDialog} from './producer-detail/producer-detail.component';
import {MatDialogModule} from "@angular/material/dialog";
import {ProducerCreatorComponent, ProducerCreatorDialog} from './producer-creator/producer-creator.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  declarations: [
    AppComponent,
    ProducersComponent,
    ProducerDetailComponent,
    ProducerDetailDialog,
    ProducerCreatorComponent,
    ProducerCreatorDialog
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
