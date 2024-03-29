import { HttpClient, HttpResponse } from '@angular/common/http'
import { Injectable, inject } from '@angular/core'
import { Contact } from '../model/contact.interface';

@Injectable({
	providedIn: 'root'
})

export class ContactService {
	private http = inject(HttpClient)

	list() {
		return this.http.get<Contact[]>('http://localHost:8080/api/contacts');
	}

	get(id: number) {
		return this.http.get<Contact>(`http://localHost:8080/api/contacts/${id}`);
	}

	create(contact: Contact) {
		return this.http.post<Contact>("http://localHost:8080/api/contacts", contact);
	}

	update(id: number, contact: Contact) {
		return this.http.put<Contact>(`http://localHost:8080/api/contacts/${id}`, contact);
	}

	delete(id: number) {
		return this.http.delete<void>(`http://localHost:8080/api/contacts/delete/${id}`);
	}
}