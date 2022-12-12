import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, forkJoin, map, Observable, of, switchMap, } from "rxjs";
import { Producer } from "./producer";
import { HttpClient } from "@angular/common/http";
import { Car, CarDetail } from "./car";
import { ProducerService } from "./producer.service";

export type ProducerName = string;

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private static readonly producerUrl = '//localhost:8080/api/producers';
  private static readonly carUrl = '//localhost:8080/api/cars';

  readonly cars$ = new BehaviorSubject<Map<ProducerName, Car[]>>(new Map());

  constructor(
    private http: HttpClient,
    private producerService: ProducerService
  ) {
    this.producerService.producers$.pipe(
      map(producers => producers.map(producer => this.getCarsByProducer(producer).pipe(
        map(cars => ([producer, cars]))
      ))),
      switchMap(cars$ => forkJoin(cars$)),
    ).subscribe(
      entries => {
        entries.forEach(([key, value]) => this.cars$.value.set(key as ProducerName, value as Car[]));
        this.cars$.next(this.cars$.value);
      }
    )
  }

  getCarsByProducer(name: Producer): Observable<Car[]> {
    if (!this.producerService.producers$.value.find(value => value === name)) {
      this.cars$.value.set(name, []);
      this.cars$.next(this.cars$.value);
    }
    return this.http.get<{
      cars: Car[]
    }>(CarService.producerUrl + '/' + name + '/cars').pipe(
      map(resp => resp.cars),
      catchError( err => {
        console.log(err);
        return of([]);
      })
    );
  }

  getCar(id: number): Observable<CarDetail> {
    return this.http.get<CarDetail>(CarService.carUrl + '/' + id);
  }

  createCar(newCar: CarDetail): void {
    this.http.post<number>(CarService.carUrl, newCar).pipe(
      map(id => ({
        ...newCar,
        id
      })),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    ).subscribe(car => {
      if (this.cars$.value.has(newCar.producer)) {
        this.cars$.value.set(newCar.producer, this.cars$.value.get(newCar.producer)!.concat(car!));
      } else {
        this.cars$.value.set(newCar.producer, [car!]);
      }
      this.cars$.next(this.cars$.value);
    });
  }

  updateCar(oldCar: CarDetail, newCar: CarDetail): Observable<any> {
    const newCars = this.cars$.value.get(newCar.producer)!
      .filter(car => car.id !== oldCar.id)
      .concat({ id: oldCar.id, name: newCar.name });
    this.cars$.value.set(newCar.producer, newCars);
    this.cars$.next(this.cars$.value);

    return this.http.put<CarDetail>(CarService.carUrl + '/' + oldCar.id, newCar).pipe(
      catchError(error => {
        console.log(error);

        const oldCars = this.cars$.value.get(newCar.producer)!
          .filter(car => car.id !== oldCar.id)
          .concat({ id: oldCar.id, name: oldCar.name });
        this.cars$.value.set(newCar.producer, oldCars);
        this.cars$.next(this.cars$.value);

        return of(null);
      })
    );
  }

  deleteCar(carToDelete: CarDetail): Observable<any> {
    const newCars = this.cars$.value.get(carToDelete.producer)!.filter(car => car.id !== carToDelete.id);
    this.cars$.value.set(carToDelete.producer, newCars);
    this.cars$.next(this.cars$.value);

    return this.http.delete<void>(CarService.carUrl + '/' + carToDelete.id).pipe(
      catchError(err => {
        console.log(err);

        const oldCars = this.cars$.value.get(carToDelete.producer)!.concat(carToDelete);
        this.cars$.value.set(carToDelete.producer, oldCars);
        this.cars$.next(this.cars$.value);

        return of(null);
      })
    );
  }
}
