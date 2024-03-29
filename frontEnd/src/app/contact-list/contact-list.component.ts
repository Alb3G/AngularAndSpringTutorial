import { Component, OnInit, inject } from '@angular/core';
import { ContactService } from '../services/contact.service';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css'
})

export default class ContactListComponent implements OnInit {
  private contacService = inject(ContactService)

  contacts: Contact[] = [];

  list() {
    this.contacService.list().subscribe((contacts) => {
      this.contacts = contacts;
    })
  }

  ngOnInit(): void {
    this.list()
  }

  delete(contact: Contact): void {
    this.contacService.delete(contact.id).subscribe(() => {
      this.list();
    });
  }
}
