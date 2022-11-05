import { Component } from '@angular/core';
import { ProducerService } from "../producer.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  public readonly producers$ = this.producerService.getProducers();
  constructor(private readonly producerService: ProducerService) { }
}
