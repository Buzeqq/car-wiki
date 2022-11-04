import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-producer-creator',
  templateUrl: './producer-creator.component.html',
  styleUrls: ['./producer-creator.component.css']
})
export class ProducerCreatorComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  createProducer(enterAnimationDuration: string, exitAnimationDuration: string): void {
    const dialogRef = this.dialog.open(ProducerCreatorDialog, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        if (result !== 'no') {
          console.log(result);
        } else if (result === 'no') {
          console.log('user did not submit form');
        }
      } else {
        console.log('result = undefined');
      }
    })
  }
}

@Component({
  selector: 'producer-creator-dialog',
  templateUrl: 'producer-creator-dialog.html',
})
export class ProducerCreatorDialog {
  constructor(public dialogRef: MatDialogRef<ProducerCreatorDialog>) {}

  onSend(form: NgForm) {
    if (form.status === 'INVALID') {
      console.log('invalid form');
    } else {
      console.log(form.value);
      this.dialogRef.close();
    }
  }
}
