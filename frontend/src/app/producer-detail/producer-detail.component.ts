import {Component, Input, OnInit} from '@angular/core';
import {Producer} from "../producer";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-producer-detail',
  templateUrl: './producer-detail.component.html',
  styleUrls: ['./producer-detail.component.css']
})

export class ProducerDetailComponent implements OnInit {

  @Input() producer?: Producer;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  deleteProducer(enterAnimationDuration: string, exitAnimationDuration: string): void {
    const dialogRef = this.dialog.open(ProducerDetailDialog, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
    });
    let producerName = dialogRef.componentInstance;
    producerName.producerName = this.producer!.name;
  }
}

@Component({
  selector: 'producer-detail-dialog',
  templateUrl: 'producer-detail-dialog.html',
})
export class ProducerDetailDialog {
  constructor(public dialogRef: MatDialogRef<ProducerDetailDialog>) {}
  producerName?: string;
}
