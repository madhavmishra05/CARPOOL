import java.util.ArrayList;
import java.util.Scanner;

public class RideBookingSystem {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Ride> totalRides = new ArrayList<>();
    static int currentUserId = 1;
    static int currentRideId = 1;
    static int currentBookingId = 1;

    private static Scanner scanner = new Scanner(System.in);

    // Create a new user account
    public static User createAccount(String name, String password, String emailId) {
        User newUser = new User(name, currentUserId++, password, emailId);
        users.add(newUser);
        System.out.println("Account created successfully!");
        System.out.println("Your User ID: " + newUser.getUser_id());
        return newUser;
    }

    // Delete a user account
    public static boolean deleteAccount(int userId, String password) {
        for (User user : users) {
            if (user.getUser_id() == userId) {
                if (user.getPassword().equals(password)) {
                    users.remove(user);
                    System.out.println("Account deleted successfully!");
                    return true;
                } else {
                    System.out.println("Incorrect password!");
                    return false;
                }
            }
        }
        System.out.println("User not found!");
        return false;
    }

    // Find user by ID
    public static User findUserById(int userId) {
        for (User user : users) {
            if (user.getUser_id() == userId) {
                return user;
            }
        }
        return null;
    }

    // Find ride by ID
    public static Ride findRideById(int rideId) {
        for (Ride ride : totalRides) {
            if (ride.getRideId() == rideId) {
                return ride;
            }
        }
        return null;
    }

    // User publishes a ride
    public static Ride publishRide(int userId, int seats, String source, String destination, long fare) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return null;
        }

        Ride newRide = user.createRide(currentRideId++, seats, source, destination, fare);
        totalRides.add(newRide);
        System.out.println("Ride published successfully!");
        System.out.println("Ride ID: " + newRide.getRideId());
        return newRide;
    }

    // Search for available rides
    public static ArrayList<Ride> searchRides(int userId, String source, String destination, int seats) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return new ArrayList<>();
        }
        return user.searchRides(source, destination, seats);
    }

    // Book a ride
    public static Booking bookRide(int userId, int rideId, int seatsToBook) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return null;
        }

        Ride ride = findRideById(rideId);
        if (ride == null) {
            System.out.println("Ride not found!");
            return null;
        }

        if (ride.getNumberOfSeats() < seatsToBook) {
            System.out.println("Not enough seats available!");
            return null;
        }

        // Book the seats
        if (ride.bookSeats(seatsToBook, userId)) {
            long totalFare = ride.getFare() * seatsToBook;
            Booking booking = new Booking(currentBookingId++, rideId, userId, seatsToBook, totalFare);
            user.addBooking(booking);
            System.out.println("Ride booked successfully!");
            System.out.println("Booking ID: " + booking.getBooking_id());
            System.out.println("Total Fare: " + totalFare);
            return booking;
        }
        return null;
    }

    // Cancel a booking
    public static boolean cancelBooking(int userId, int bookingId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        for (Booking booking : user.getBookings()) {
            if (booking.getBooking_id() == bookingId) {
                booking.cancelBooking();
                System.out.println("Booking cancelled successfully!");
                return true;
            }
        }
        System.out.println("Booking not found!");
        return false;
    }

    // View all available rides
    public static void viewAllRides() {
        if (totalRides.isEmpty()) {
            System.out.println("No rides available!");
            return;
        }
        System.out.println("\n========== AVAILABLE RIDES ==========");
        for (Ride ride : totalRides) {
            System.out.println(ride);
            System.out.println("---");
        }
    }

    // View user profile
    public static void viewUserProfile(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        System.out.println("\n========== USER PROFILE ==========");
        System.out.println(user);
    }

    // View user's bookings
    public static void viewUserBookings(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        System.out.println();
        user.viewBookings();
    }
}
