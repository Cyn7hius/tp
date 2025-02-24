package seedu.fast.model.person;

import static seedu.fast.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import seedu.fast.commons.util.TagUtil;
import seedu.fast.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private final Appointment appointment;
    private AppointmentCount count;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                  Appointment appointment, AppointmentCount count) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.appointment = appointment;
        this.count = count;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public AppointmentCount getCount() {
        return count;
    }

    /**
     * Returns the highest priority of all the tags in person.
     * @return the highest priority
     */
    public int getPriority() {
        int temp = TagUtil.NO_PRIORITY;
        if (tags.isEmpty()) {
            return temp;
        }
        Iterator<Tag> itr = tags.iterator();
        while (itr.hasNext()) {
            int prio = itr.next().getPriority();
            if (prio < temp) {
                temp = prio;
            }
        }
        return temp;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; \nPhone: ")
                .append(getPhone())
                .append("; \nEmail: ")
                .append(getEmail())
                .append("; \nAddress: ")
                .append(getAddress())
                .append("; \nRemark: ")
                .append(getRemark())
                .append("; \n")
                .append(getAppointment());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; \nTags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
