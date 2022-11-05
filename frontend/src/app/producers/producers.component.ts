import { Component } from '@angular/core';
import { ProducerService } from "../producer.service";
import { MatDialog } from "@angular/material/dialog";
import { ProducerCreateFormDialogComponent } from "../producer-create-form-dialog/producer-create-form-dialog.component";

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
    this.openDialog('0ms', '0ms');
  }

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string) {
    const dialogRef = this.dialog.open(ProducerCreateFormDialogComponent, {
      enterAnimationDuration,
      exitAnimationDuration,
    });

    dialogRef.afterClosed().subscribe(
      producer => {
        if (producer) {
          this.producerService.createProducer(producer).subscribe();
        }
      }
    )
  }
}
