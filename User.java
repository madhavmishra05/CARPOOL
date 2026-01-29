import java.util.ArrayList;

public class User {
    private String name;
    private int user_id;
    private String password;
    private String emailId;
    private ArrayList<Booking> bookings;

    public User(String name, int user_id, String password, String emailId) {
        this.name = name;
        this.emailId = emailId;
        this.user_id = user_id;
        this.password = password;
        this.bookings = new ArrayList<>();
    }

    // Getters
    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    // Search for available rides
    public ArrayList<Ride> searchRides(String source, String destination, int seats) {
        ArrayList<Ride> searchedRides = new ArrayList<>();

        for (Ride ride : RideBookingSystem.totalRides) {
            if (ride.getSource().equals(source) &&
                    ride.getDestination().equals(destination) &&
                    ride.getNumberOfSeats() >= seats &&
                    ride.getNumberOfSeats() > 0) {
                searchedRides.add(ride);
            }
        }
        return searchedRides;
    }

    // Create a new ride
    public Ride createRide(int rideId, int seats, String source, String destination, long fare) {
        return new Ride(rideId, user_id, seats, source, destination, fare);
    }

    // Add booking to user's bookings
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    // Get user booking history
    public void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("--- Booking History for " + name + " ---");
        for (Booking booking : bookings) {
            System.out.println(booking);
            System.out.println("---");
        }
    }

    @Override
    public String toString() {
        return "User_Id : " + user_id +
                "\nName : " + name +
                "\nEmailId : " + emailId;
    }
}
