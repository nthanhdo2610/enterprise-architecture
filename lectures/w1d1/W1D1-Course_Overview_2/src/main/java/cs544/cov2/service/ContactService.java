package cs544.cov2.service;

import cs544.cov2.dao.ContactDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.cov2.domain.Contact;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    public Iterable<Contact> getContacts() {
        return contactDao.findAll();
    }

    public Contact getContact(Long id) {
        return contactDao.getById(id);
    }

    public void addContact(Contact contact) {
        contactDao.save(contact);
    }

    public void updateContact(Contact contact) {
        // manually merge the data
        Contact db = contactDao.getById(contact.getId());
        db.setName(contact.getName());
    }

    public void deleteContact(Contact contact) {
        contactDao.delete(contact);
    }
}
