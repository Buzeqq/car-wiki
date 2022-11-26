import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from "rxjs";
import { Producer } from "./producer";
import { HttpClient } from "@angular/common/http";
import { Car, CarDetail } from "./car";
import { ProducerService } from "./producer.service";

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private static readonly producerUrl = '//localhost:8080/api/producers';
  private static readonly carUrl = '//localhost:8080/api/cars';

  readonly cars$ = new BehaviorSubject<Map<string, Car[]>>(new Map<string, Car[]>());

  constructor(
    private http: HttpClient,
    private producerService: ProducerService
  ) {
    this.producerService.producers$.subscribe(producers => {
      producers.forEach(producer => {
        this.getCarsByProducer(producer).subscribe(cars => {
          this.cars$.value.set(producer, cars);
        })
      })
    })

    this.cars$.next(this.cars$.value);
    console.log()
  }

  getCarsByProducer(name: Producer) {
    return this.http.get<{
      cars: Car[]
    }>(CarService.producerUrl + '/' + name + '/cars').pipe(
      map(resp => resp.cars)
    );
  }

  getCar(id: number): Observable<CarDetail> {
    return this.http.get<CarDetail>(CarService.carUrl + '/' + id);
  }

  createCar(newCar: CarDetail): Observable<any> {
    const newCars = this.cars$.value.get(newCar.producer)!.concat({ id: newCar.id, name: newCar.name });
    this.cars$.value.set(newCar.producer, newCars);
    this.cars$.next(this.cars$.value);

    return this.http.post<CarDetail>(CarService.carUrl, newCar).pipe(
      catchError(err => {
        console.log(err);

        const oldCars = this.cars$.value.get(newCar.producer)!.filter(car => car.id !== newCar.id);
        this.cars$.value.set(newCar.producer, oldCars);
        this.cars$.next(this.cars$.value);

        return of(null);
      })
    );
  }

  updateCar(oldCar: CarDetail, newCar: CarDetail): Observable<any> {
    const newCars = this.cars$.value.get(newCar.producer)!
      .filter(car => car.id !== oldCar.id)
      .concat({ id: newCar.id, name: newCar.name });
    this.cars$.value.set(newCar.producer, newCars);
    this.cars$.next(this.cars$.value);

    return this.http.put<CarDetail>(CarService.carUrl + '/' + oldCar.id, newCar).pipe(
      catchError(error => {
        console.log(error);

        const oldCars = this.cars$.value.get(newCar.producer)!
          .filter(car => car.id !== newCar.id)
          .concat({ id: oldCar.id, name: oldCar.name });
        this.cars$.value.set(newCar.producer, oldCars);
        this.cars$.next(this.cars$.value);

        return of(null);
      })
    );
  }

  deleteCar(car: CarDetail): Observable<any> {
    const newCars = this.cars$.value.get(car.producer)!
      .filter(car => car.id !== car.id);
    this.cars$.value.set(car.producer, newCars);
    this.cars$.next(this.cars$.value);

    return this.http.delete<Car>(CarService.carUrl + '/' + car.id).pipe(
      catchError(err => {
        console.log(err);

        const oldCars = this.cars$.value.get(car.producer)!.concat(car);
        this.cars$.value.set(car.producer, oldCars);
        this.cars$.next(this.cars$.value);

        return of(null);
      })
    );
  }
}
