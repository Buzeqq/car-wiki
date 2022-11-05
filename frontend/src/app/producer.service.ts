import { Injectable } from '@angular/core';
import { Producer, ProducerDetail } from "./producer";
import { map, Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProducerService {

  constructor(private http: HttpClient) { }

  private producerUrl = '//localhost:8080/api/producers';

  getProducers(): Observable<Producer[]> {
    return this.http.get<{
      producers: Producer[]
    }>(this.producerUrl).pipe(
      map(resp => resp.producers)
    );
  }

  getProducer(name: string): Observable<ProducerDetail> {
    return this.http.get<ProducerDetail>(this.producerUrl + '/' + name);
  }
}
