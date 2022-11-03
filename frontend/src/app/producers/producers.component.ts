import { Component, OnInit } from '@angular/core';
import { PRODUCERS } from "../mock-producers";
import {Producer} from "../producer";

@Component({
  selector: 'app-producers',
  templateUrl: './producers.component.html',
  styleUrls: ['./producers.component.css']
})
export class ProducersComponent implements OnInit {

  producers: Producer[] = PRODUCERS;

  constructor() { }

  ngOnInit(): void {
  }

  selectedProducer?: Producer;
  onSelect(producer: Producer) {
    this.selectedProducer = producer;
  }
}
