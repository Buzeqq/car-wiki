import { Injectable } from '@angular/core';
import { Producer, ProducerDetail } from "./producer";
import {BehaviorSubject, catchError, map, Observable, of, shareReplay} from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProducerService {

  private producerUrl = '//localhost:8080/api/producers';

  private readonly producersSubject$ = new BehaviorSubject<Producer[]>([]);

  public readonly producers$ = this.producersSubject$.asObservable().pipe(
    shareReplay(1)
  );

  constructor(private http: HttpClient) {
    this.getProducers().subscribe(this.producersSubject$);
  }

  private getProducers(): Observable<Producer[]> {
    return this.http.get<{
      producers: Producer[]
    }>(this.producerUrl).pipe(
      map(resp => resp.producers)
    );
  }

  getProducer(name: string): Observable<ProducerDetail> {
    return this.http.get<ProducerDetail>(this.producerUrl + '/' + name);
  }

  deleteProducer(name: string): Observable<any> {
    const tmp = this.producersSubject$.value.filter(producer => producer !== name);
    this.producersSubject$.next(tmp);

    return this.http.delete<Producer>(this.producerUrl + '/' + name).pipe(
      catchError(error => {
        console.log(error);
        this.producersSubject$.next(this.producersSubject$.value.concat(name));
        return of(null);
      })
    );
  }

  createProducer(newProducer: ProducerDetail): Observable<any> {
    const tmp = this.producersSubject$.value.concat(newProducer.name);
    this.producersSubject$.next(tmp);

    return this.http.post<ProducerDetail>(this.producerUrl, newProducer);
  }
}
