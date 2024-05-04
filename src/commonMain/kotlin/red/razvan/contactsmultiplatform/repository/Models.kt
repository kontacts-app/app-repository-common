package red.razvan.contactsmultiplatform.repository

import com.benasher44.uuid.uuid4

data class ContactId(
    val value: String = uuid4().toString()
)

data class Contact(
    val id: ContactId = ContactId(),
    val name: String
)

data class ContactsSection(
    val initial: Char,
    val contacts: List<Contact>
)

data class NewContact(val name: String)