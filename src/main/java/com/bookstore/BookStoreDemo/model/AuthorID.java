
package com.bookstore.BookStoreDemo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorID implements Serializable {    
    
    private String firstName;
    private String lastName;

    public AuthorID() {
        
    }
    
    public AuthorID(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorID)) return false;
        AuthorID that = (AuthorID) o;
        return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName());
    }
   
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}
