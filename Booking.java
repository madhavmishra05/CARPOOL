public class Booking {
    private int booking_id;
    private int ride_id;
    private int user_id;
    private int numberOfSeats;
    private long totalFare;
    private String bookingStatus;

    public Booking(int booking_id, int ride_id, int user_id, int numberOfSeats, long totalFare) {
        this.booking_id = booking_id;
        this.ride_id = ride_id;
        this.user_id = user_id;
        this.numberOfSeats = numberOfSeats;
        this.totalFare = totalFare;
        this.bookingStatus = "CONFIRMED";
    }

    // Getters
    public int getBooking_id() {
        return booking_id;
    }

    public int getRide_id() {
        return ride_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public long getTotalFare() {
        return totalFare;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    // Cancel booking
    public void cancelBooking() {
        this.bookingStatus = "CANCELLED";
    }

    @Override
    public String toString() {
        return "Booking ID : " + booking_id +
                "\nRide ID : " + ride_id +
                "\nUser ID : " + user_id +
                "\nNumber of Seats : " + numberOfSeats +
                "\nTotal Fare : " + totalFare +
                "\nStatus : " + bookingStatus;
    }
}
