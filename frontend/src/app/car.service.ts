import { Injectable } from '@angular/core';
import { map, Observable } from "rxjs";
import {Producer} from "./producer";
import {HttpClient} from "@angular/common/http";
import {Car} from "./car";

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private static readonly producerUrl = '//localhost:8080/api/producers';
  private static readonly carUrl = '//localhost:8080/api/cars';

  constructor(private http: HttpClient) { }

  public getCars(): Observable<Car[]> {
    return this.http.get<{
      cars: Car[]
    }>(CarService.carUrl).pipe(
      map(resp => resp.cars)
    );
  }

  public getCarsByProducer(name: Producer) {
    return this.http.get<{
      cars: Car[]
    }>(CarService.producerUrl + '/' + name + '/cars').pipe(
      map(resp => resp.cars)
    );
  }
}
