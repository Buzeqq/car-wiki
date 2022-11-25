import { Component } from '@angular/core';
import { ProducerDetail } from "../producer";
import { ActivatedRoute } from "@angular/router";
import { ProducerService } from "../producer.service";
import { Location } from "@angular/common";
import {MatDialog} from "@angular/material/dialog";
import {ProducerCreateFormDialogComponent} from "../producer-create-form-dialog/producer-create-form-dialog.component";
import {filter, switchMap, tap} from "rxjs";

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

  // deleteProducer(producer: ProducerDetail): void {
  //   this.openDeleteDialog(producer);
  // }

  editProducer(producer: ProducerDetail): void {
    this.openEditDialog(producer);
  }

  private openEditDialog(producer: ProducerDetail): void {
    const oldProducer = producer;
    const dialogRef = this.dialog.open(ProducerCreateFormDialogComponent, {
        data: {
          producer,
          isEdit: true
        }
      }
    );

    dialogRef.afterClosed().pipe(
      filter(Boolean),
      tap(() => this.goBack()),
      switchMap(newProducer => this.producerService.updateProducer(oldProducer, newProducer))
    ).subscribe();
  }

  // private openDeleteDialog(producer: ProducerDetail): void {
  //   const dialogRef = this.dialog.open(ProducerDetailDeleteDialogComponent, {
  //       data: producer
  //     }
  //   );
  //
  //   dialogRef.afterClosed().pipe(
  //     filter(Boolean),
  //     tap(() => this.goBack()),
  //     switchMap(() => this.producerService.deleteProducer(producer.name))
  //   ).subscribe();
  // }
}
