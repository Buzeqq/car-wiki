import {Injectable} from '@angular/core';
import {PRODUCERS} from "./mock-producers";
import {Producer} from "./producer";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProducerService {

  constructor() { }

  getProducers(): Observable<Producer[]> {
    return of(PRODUCERS);
  }
}
