import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProducerDetail} from "../producer";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-producer-create-form-dialog',
  templateUrl: './producer-create-form-dialog.component.html',
  styleUrls: ['./producer-create-form-dialog.component.css']
})
export class ProducerCreateFormDialogComponent implements OnInit {
  public form: FormGroup = this.fb.group({
    name: this.producer ? this.producer.name : '',
    foundationYear: this.producer ? this.producer.foundationYear : ''
  });

  constructor(
    private dialogRef: MatDialogRef<ProducerCreateFormDialogComponent>,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public producer?: ProducerDetail
  ) { }

  ngOnInit() {

  }

  abort() {
    this.dialogRef.close();
  }

  create() {
    this.dialogRef.close(this.form.value);
  }
}
