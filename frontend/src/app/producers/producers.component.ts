import { Component } from '@angular/core';
import { ProducerService } from "../producer.service";
import { MatDialog } from "@angular/material/dialog";
import { ProducerCreateFormDialogComponent } from "../producer-create-form-dialog/producer-create-form-dialog.component";
import {filter, switchMap} from "rxjs";

@Component({
  selector: 'app-producers',
  templateUrl: './producers.component.html',
  styleUrls: ['./producers.component.css']
})
export class ProducersComponent {
  constructor(
    public readonly producerService: ProducerService,
    public dialog: MatDialog
  ) { }

  createProducer() {
    this.openDialog();
  }

  openDialog() {
    const dialogRef = this.dialog.open(ProducerCreateFormDialogComponent);

    dialogRef.afterClosed().pipe(
      filter(Boolean),
      switchMap(producer => this.producerService.createProducer(producer))
    ).subscribe();
  }

  // deleteProducer(producer: string): void {
  //   this.openDeleteDialog(producer);
  // }

  // private openDeleteDialog(producer: string): void {
  //   const dialogRef = this.dialog.open(ProducerDetailDeleteDialogComponent, {
  //       data: producer
  //     }
  //   );
  //
  //   dialogRef.afterClosed().pipe(
  //     filter(Boolean),
  //     switchMap(() => this.producerService.deleteProducer(producer))
  //   ).subscribe();
  // }
}
