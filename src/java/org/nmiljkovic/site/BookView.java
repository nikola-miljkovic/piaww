package org.nmiljkovic.site;

import org.nmiljkovic.dao.BookingRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class BookView {
    
    private String mFirstName;
    private String mLastName;
    private long passport;
    private String creditCardNumber;
    private int count;
    private int flightId;
    private String bookCode;
    
    public BookView() {
    }
    
    public String submit() {
        if (passport == 0 || creditCardNumber.isEmpty()) {
            return null;
        }
        
        BookingRepository bookingRepo = new BookingRepository();
        String bookCode = bookingRepo.bookFlight(flightId, mFirstName, mLastName, passport, creditCardNumber, count);
        return "booking?faces-redirect=true&bookCode=" + bookCode;
    }
    
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardnumber) {
        this.creditCardNumber = creditCardnumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
    
    
}