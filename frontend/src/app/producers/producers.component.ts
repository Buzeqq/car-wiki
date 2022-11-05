import { Component } from '@angular/core';
import { ProducerService } from "../producer.service";

@Component({
  selector: 'app-producers',
  templateUrl: './producers.component.html',
  styleUrls: ['./producers.component.css']
})
export class ProducersComponent {
  public readonly producers$ = this.producerService.getProducers();
  constructor(private readonly producerService: ProducerService) { }
}
