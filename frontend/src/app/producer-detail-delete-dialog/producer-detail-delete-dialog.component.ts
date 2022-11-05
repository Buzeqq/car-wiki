import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { ProducerDetail } from "../producer";

@Component({
  selector: 'app-producer-detail-delete-dialog',
  templateUrl: './producer-detail-delete-dialog.component.html',
  styleUrls: ['./producer-detail-delete-dialog.component.css']
})
export class ProducerDetailDeleteDialogComponent {
  constructor(public dialogRef: MatDialogRef<ProducerDetailDeleteDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public producer: ProducerDetail) {
  }

  delete() {
    this.dialogRef.close(true);
  }

  abort() {
    this.dialogRef.close(false);
  }
}
