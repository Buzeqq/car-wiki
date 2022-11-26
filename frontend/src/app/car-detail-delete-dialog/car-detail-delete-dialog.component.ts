import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import {
  ProducerDetailDeleteDialogComponent
} from "../producer-detail-delete-dialog/producer-detail-delete-dialog.component";
import { CarDetail } from "../car";

@Component({
  selector: 'app-car-detail-delete-dialog',
  templateUrl: './car-detail-delete-dialog.component.html',
  styleUrls: ['./car-detail-delete-dialog.component.css']
})
export class CarDetailDeleteDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ProducerDetailDeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public car: CarDetail
  ) { }

  delete() {
    this.dialogRef.close(true);
  }

  abort() {
    this.dialogRef.close(false);
  }
}
