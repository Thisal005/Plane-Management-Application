class Ticket {
    // Private instance variables.
    private String rowLetter;
    private int seatNum;
    private int price;
    private Person person;

    // Constructor to initialize the Ticket object.
    public Ticket(String rowLetter, int seatNum, int price, Person person) {
        this.rowLetter = rowLetter;
        this.seatNum = seatNum;
        this.price = price;
        this.person = person;
    }

    // Getter and setter methods for rowLetter.
    public String getRowLetter() {
        return this.rowLetter;
    }

    public void setRowLetter(String rowLetter) {
        this.rowLetter = rowLetter;
    }

    // Getter and setter methods for seatNum.
    public int getSeatNum() {
        return this.seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    // Getter and setter methods for price.
    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Method to determine the price based on the seat number.
    public int priceChecker(int seatNumber) {
        int price;
        if (seatNumber <= 5) {
            price = 200;
        } else if (seatNumber > 5 && seatNumber < 9) {
            price = 150;
        } else {
            price = 180;
        }
        return price;
    }

    // Method to print ticket information.
    public void printTicketInformation() {
        System.out.println("\n-----  TICKET INFORMATION  ------");
        System.out.println("Row Letter: " + rowLetter);
        System.out.println("Seat Number: " + seatNum);
        System.out.println("Price: £" + price);
        // Call the information method of the Person object.
        person.information();
    }
}
