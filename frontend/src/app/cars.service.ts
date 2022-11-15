import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from "rxjs";
import { Car, CarDetail } from "./car";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  private static readonly carsUrl = '//localhost:8080/api/cars';

  readonly cars$ = new BehaviorSubject<Car[]>([]);

  constructor(private http: HttpClient) {
    this.getCars().subscribe(cars => {
      this.cars$.next(cars);
    });
  }

  private getCars(): Observable<Car[]> {
    return this.http.get<{
      cars: Car[]
    }>(CarsService.carsUrl).pipe(
      map(resp => resp.cars)
    );
  }

  getCar(id: number): Observable<CarDetail> {
    return this.http.get<CarDetail>(CarsService.carsUrl + '/' + id);
  }

  deleteProducer(id: number): Observable<any> {
    const toDeleteCar = this.cars$.value.find(car => car.id === id);
    this.cars$.next(this.cars$.value.filter(car => car.id !== id));

    return this.http.delete<Car>(CarsService.carsUrl + '/' + id).pipe(
      catchError(error => {
        console.log(error);
        this.cars$.next(this.cars$.value.concat(toDeleteCar!));
        return of(null);
      })
    )
  }

  createCar(newCar: CarDetail): Observable<any> {
    this.cars$.next(this.cars$.value.concat(newCar));

    return this.http.post<CarDetail>(CarsService.carsUrl, newCar).pipe(
      catchError(error => {
        console.log(error);
        this.cars$.next(this.cars$.value.filter(car => car.id !== newCar.id));
        return of(null);
      })
    );
  }

  // updateCar(oldCar: CarDetail, newCar: CarDetail): Observable<any> {
  //   return of(null);
  // }
}
