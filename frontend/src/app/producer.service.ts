import { Injectable } from '@angular/core';
import { Producer, ProducerDetail } from "./producer";
import { BehaviorSubject, catchError, map, Observable, of } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProducerService {
  private static readonly producerUrl = '//localhost:8080/api/producers';

  readonly producers$ = new BehaviorSubject<Producer[]>([]);

  constructor(private http: HttpClient) {
    this.getProducers().subscribe(producers => {
      this.producers$.next(producers);
    });
  }

  private getProducers(): Observable<Producer[]> {
    return this.http.get<{
      producers: Producer[]
    }>(ProducerService.producerUrl).pipe(
      map(resp => resp.producers)
    );
  }

  getProducer(name: string): Observable<ProducerDetail> {
    return this.http.get<ProducerDetail>(ProducerService.producerUrl + '/' + name);
  }

  deleteProducer(name: string): Observable<any> {
    this.producers$.next(this.producers$.value.filter(producer => producer !== name));

    return this.http.delete<Producer>(ProducerService.producerUrl + '/' + name).pipe(
      catchError(error => {
        console.log(error);
        this.producers$.next(this.producers$.value.concat(name));
        return of(null); // Tu kiedyś jakiś error do UI
      })
    );
  }

  createProducer(newProducer: ProducerDetail): Observable<any> {
    this.producers$.next(this.producers$.value.concat(newProducer.name));

    return this.http.post<ProducerDetail>(ProducerService.producerUrl, newProducer).pipe(
      catchError(() => {
        this.producers$.next(this.producers$.value.filter(producer => producer !== newProducer.name));
        return of(null);
      })
    );
  }

  updateProducer(oldProducer: ProducerDetail, newProducer: ProducerDetail): Observable<any> {
    this.producers$.next(this.producers$.value
      .filter(producer => oldProducer.name !== producer)
      .concat(newProducer.name));

    return this.http.put<ProducerDetail>(ProducerService.producerUrl + '/' + oldProducer.name, newProducer).pipe(
      catchError(error => {
        console.log(error);

        this.producers$.next(this.producers$.value
          .filter(producer => newProducer.name !== producer)
          .concat(newProducer.name));

        this.producers$.next(this.producers$.value);
        return of(null); // Tu też  kiedyś jakiś error do UI
      })
    );
  }
}
