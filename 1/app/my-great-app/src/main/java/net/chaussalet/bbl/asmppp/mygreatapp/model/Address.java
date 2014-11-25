package net.chaussalet.bbl.asmppp.mygreatapp.model;

public class Address implements Comparable<Address> {
    public int id;
    public String firstname;
    public String lastname;
    public String email;

    @Override
    public int compareTo(Address o) {
        int comparison = lastname.compareTo(o.lastname);
        if (comparison == 0)
            comparison = firstname.compareTo(o.firstname);
        if (comparison == 0)
            comparison = email.compareTo(o.email);
        return comparison;
    }
}
