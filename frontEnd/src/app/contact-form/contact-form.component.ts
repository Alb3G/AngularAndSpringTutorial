import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ContactService } from '../services/contact.service';
import { Contact } from '../model/contact.interface';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})
export default class ContactFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private contactService = inject(ContactService);

  form?: FormGroup;
  contact?: Contact;

  ngOnInit(): void {
    const ID = this.route.snapshot.paramMap.get('id');

    if (ID) {
      this.contactService.get(parseInt(ID))
        .subscribe(contact => {
          this.contact = contact;
          this.form = this.fb.group({
            name: [contact.name, Validators.required],
            email: [contact.email, Validators.email]
          })
        })
    } else {
      this.form = this.fb.group({
        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]]
      })
    }
  }

  save(): void {
    if (this.form?.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    let request: Observable<Contact>;
    const contactForm = this.form!.value;

    if (this.contact) {
      request = this.contactService.update(this.contact.id, contactForm);
    } else {
      request = this.contactService.create(contactForm);
    }

    request.subscribe({
      next: () => {
        this.router.navigate(['/'])
      },
      error: errResponse => {
        console.log('ErrorResponse', errResponse.error.errors)
      }
    })
  }
}
