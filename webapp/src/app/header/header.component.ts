import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  ngOnInit(): void {
    this.activeUser = "George"
  }

  activeUser: any;
  onLogout() {
    throw new Error('Method not implemented.');
  }
}
