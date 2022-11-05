import { Component } from '@angular/core';
import { ProducerDetail } from "../producer";
import { ActivatedRoute } from "@angular/router";
import { ProducerService } from "../producer.service";
import { Location } from "@angular/common";
import {MatDialog} from "@angular/material/dialog";
import {
  ProducerDetailDeleteDialogComponent
} from "../producer-detail-delete-dialog/producer-detail-delete-dialog.component";

@Component({
  selector: 'app-producer-detail',
  templateUrl: './producer-detail.component.html',
  styleUrls: ['./producer-detail.component.css']
})

export class ProducerDetailComponent {
  public readonly producerDetail$ = this.producerService.getProducer(this.route.snapshot.paramMap.get('name')!);

  constructor(
    private route: ActivatedRoute,
    private producerService: ProducerService,
    private location: Location,
    public dialog: MatDialog
  ) { }

  goBack(): void {
    this.location.back();
  }

  deleteProducer(producer: ProducerDetail): void {
    this.openDialog('0ms', '0ms', producer);
  }

  private openDialog(enterAnimationDuration: string, exitAnimationDuration: string, producer: ProducerDetail): void {
    const dialogRef = this.dialog.open(ProducerDetailDeleteDialogComponent, {
        width: '250px',
        enterAnimationDuration,
        exitAnimationDuration,
        data: producer
      }
    );

    dialogRef.afterClosed().subscribe(
      confirmation => {
        if (confirmation) {
          this.producerService.deleteProducer(producer.name).subscribe();
          this.goBack();
        }
      }
    );
  }
}
