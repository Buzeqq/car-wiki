import { Component, OnInit } from '@angular/core';
import {Producer} from "../producer";
import {ProducerService} from "../producer.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  producers: Producer[] = [];

  constructor(public producerService: ProducerService) { }

  ngOnInit(): void {
    this.getTop3Producers();
  }

  getTop3Producers(): void {
    this.producerService.getProducers().subscribe(
      producers => this.producers = producers.slice(1, 4)
    );
  }
}
