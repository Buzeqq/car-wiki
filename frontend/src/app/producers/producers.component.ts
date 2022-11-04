import { Component, OnInit } from '@angular/core';
import {Producer} from "../producer";
import {ProducerService} from "../producer.service";

@Component({
  selector: 'app-producers',
  templateUrl: './producers.component.html',
  styleUrls: ['./producers.component.css']
})
export class ProducersComponent implements OnInit {

  producers: Producer[] = [];

  constructor(private producerService: ProducerService) { }

  ngOnInit(): void {
    this.getProducers();
  }

  getProducers(): void {
    this.producerService.getProducers()
      .subscribe(producers => this.producers = producers);
  }
}
