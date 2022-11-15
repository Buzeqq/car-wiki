import { Producer } from "./producer";

export interface Car {
  id: number;
  name: string;
}

export interface CarDetail {
  id: number;
  name: string;
  producer: Producer;
  horsePower: number;
}
