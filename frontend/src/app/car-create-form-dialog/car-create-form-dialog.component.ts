import {Component, Inject, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { CarDetail } from "../car";
import { Producer } from "../producer";

@Component({
  selector: 'app-car-create-form-dialog',
  templateUrl: './car-create-form-dialog.component.html',
  styleUrls: ['./car-create-form-dialog.component.css']
})
export class CarCreateFormDialogComponent implements OnInit {

  public form: FormGroup = this.fb.group({
    name: this.data?.car.name ? this.data.car.name : '',
    horsePower: this.data?.car.horsePower ? this.data.car.horsePower : '',
    producer: this.data?.car.producer ? this.data.car.producer : ''
  });

  constructor(
    private dialogRef: MatDialogRef<CarCreateFormDialogComponent>,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data?: {
      isEdit: true,
      car: CarDetail,
      producers: Producer[]
    }
  ) { }

  ngOnInit(): void {
    if (this.data?.isEdit) {
      this.form.get('producer')?.disable();
    }
  }

  abort() {
    this.dialogRef.close();
  }

  create() {
    this.form.get('producer')?.enable();
    this.dialogRef.close(this.form.value);
  }
}
