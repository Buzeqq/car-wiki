import {Injectable} from '@angular/core';
import {Producer, ProducerDetail} from "./producer";
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PRODUCERS} from "./mock-producers";

@Injectable({
  providedIn: 'root'
})
export class ProducerService {

  constructor(private http: HttpClient) { }

  private producerUrl = 'api/producers';

  getProducers(): Observable<Producer[]> {
    // return this.http.get<Producer[]>(this.producerUrl);
    return of(PRODUCERS.map<Producer>(producerDetail => ({name: producerDetail.name})));
  }

  getProducer(name: string): Observable<ProducerDetail> {
    // return this.http.get<ProducerDetail>(this.producerUrl + '/' + name);
    return of(PRODUCERS.find(producer => producer.name === name)!);
  }
}
