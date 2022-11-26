import { Component } from '@angular/core';
import { CarService } from "../car.service";
import { Location } from "@angular/common";
import { CarDetail } from "../car";
import { ActivatedRoute } from "@angular/router";
import { filter, switchMap, tap } from "rxjs";
import { MatDialog } from "@angular/material/dialog";
import { CarDetailDeleteDialogComponent } from "../car-detail-delete-dialog/car-detail-delete-dialog.component";
import { CarCreateFormDialogComponent } from "../car-create-form-dialog/car-create-form-dialog.component";
import { ProducerService } from "../producer.service";

@Component({
  selector: 'app-car-detail',
  templateUrl: './car-detail.component.html',
  styleUrls: ['./car-detail.component.css']
})
export class CarDetailComponent {
  public readonly carDetail$ = this.carService.getCar(parseInt(this.route.snapshot.queryParamMap.get('id')!));
  public readonly producers$ = this.producerService.producers$;

  constructor(
    private carService: CarService,
    private producerService: ProducerService,
    private route: ActivatedRoute,
    private location: Location,
    public dialog: MatDialog,
    ) { }

  goBack(): void {
    this.location.back();
  }

  deleteCar(car: CarDetail) {
    this.openDeleteDialog(car);
  }

  editCar(car: CarDetail) {
    this.openEditDialog(car);
  }

  private openEditDialog(car: CarDetail): void {
    const oldCar = car;
    const dialogRef = this.dialog.open(CarCreateFormDialogComponent, {
        data: {
          car,
          isEdit: true,
          producers: this.producerService.producers$.value
        }
      }
    );

    dialogRef.afterClosed().pipe(
      filter(Boolean),
      tap(() => this.goBack()),
      switchMap(newCar => this.carService.updateCar(oldCar, newCar))
    ).subscribe();
  }

  private openDeleteDialog(car: CarDetail): void {
    const dialogRef = this.dialog.open(CarDetailDeleteDialogComponent, {
        data: car
      }
    );

    dialogRef.afterClosed().pipe(
      filter(Boolean),
      tap(() => this.goBack()),
      switchMap(() => this.carService.deleteCar(car.id))
    ).subscribe();
  }
}
