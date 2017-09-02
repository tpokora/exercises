import { Component } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {

  public visible = false;
  private visibleAnimate = false;

  constructor() { }

  show() {
    this.visible = true;
    setTimeout(() => this.visibleAnimate = true, 100);
  }

  hide() {
    this.visible = false;
    setTimeout(() => this.visibleAnimate = false, 300);
  }

  onContainerClicked(event: MouseEvent) {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }
}
