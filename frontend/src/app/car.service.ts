import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from "rxjs";
import { Producer } from "./producer";
import { HttpClient } from "@angular/common/http";
import { Car, CarDetail } from "./car";

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private static readonly producerUrl = '//localhost:8080/api/producers';
  private static readonly carUrl = '//localhost:8080/api/cars';

  constructor(private http: HttpClient) { }

  getCars(): Observable<Car[]> {
    return this.http.get<{
      cars: Car[]
    }>(CarService.carUrl).pipe(
      map(resp => resp.cars)
    );
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
    return this.http.post<CarDetail>(CarService.carUrl, newCar).pipe(
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }

  updateCar(oldCar: CarDetail, newCar: CarDetail): Observable<any> {
    return this.http.put<CarDetail>(CarService.carUrl + '/' + oldCar.id, newCar).pipe(
      catchError(error => {
        console.log(error);
        return of(null);
      })
    );
  }

  deleteCar(id: number): Observable<any> {
    return this.http.delete<Car>(CarService.carUrl + '/' + id).pipe(
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }

  deleteCarByProducer(id: number, producer: Producer): Observable<any> {
    return this.http.delete<Car>(CarService.producerUrl + '/' + producer + '/cars/' + id).pipe(
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }

  createCarByProducer(newCar: CarDetail, producer: Producer): Observable<any> {
    return this.http.post<CarDetail>(CarService.producerUrl + '/' + producer + '/cars', newCar).pipe(
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }
}
