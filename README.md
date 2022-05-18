# Assignment 2
## Details
Course: Further Programming

Assignment: 2

Group: 15

## Members:
- Nguyen Anh Tuan - s3817907
- Nguyen Viet Lam Tung - s3891452
- Duong Tran Le Truong - s3807528

## Class Diagram
``` mermaid
classDiagram
direction BT
class AppConfig {
  + AppConfig()
  + transactionManager(SessionFactory) HibernateTransactionManager
  + sessionFactory() LocalSessionFactoryBean
}
class BadRequestException {
  + BadRequestException()
}
class Booking {
  + Booking(Long, String, String, LocalDateTime, LocalDateTime, Long, Customer, Driver, Invoice, ZonedDateTime)
  + Booking()
  + Booking(String, String, LocalDateTime, LocalDateTime, Long, Customer, Driver, Invoice, ZonedDateTime)
  + Long id
  + String endLoc
  + LocalDateTime drop
  + LocalDateTime pickup
  + Customer customer
  + Driver driver
  + Invoice invoice
  + ZonedDateTime dateCreated
  + Long distance
  + String startLoc
  + toString() String
  + Invoice invoice
  + Long distance
  + Long id
  + String endLoc
  + Driver driver
  + LocalDateTime pickup
  + Customer customer
  + LocalDateTime drop
  + ZonedDateTime dateCreated
  + String startLoc
}
class BookingController {
  + BookingController()
  + BookingService bookingService
  + saveBooking(Booking, Long, Long) Booking
  + deleteBooking(Long) String
  + getSingleBooking(Long) Booking
  + updateBooking(Long, Booking) Booking
  + getAllBooking(Integer, Integer, String, String) List~Booking~
  + BookingService bookingService
}
class BookingService {
  + BookingService()
  + SessionFactory sessionFactory
  + CustomerService customerService
  + DriverService driverService
  + getAllBooking(Integer, Integer, String, String) List~Booking~
  + getSingleBooking(Long) Booking
  + updateBooking(Long, Booking) Booking
  + deleteBooking(Long) String
  + saveBooking(Booking, Long, Long) Booking
  + SessionFactory sessionFactory
  + DriverService driverService
  + CustomerService customerService
}
class Car {
  + Car(String, String, String, String, String, Double, String, Double, Driver, ZonedDateTime)
  + Car()
  + Car(Long, String, String, String, String, String, Double, String, Double, Driver, ZonedDateTime)
  + Driver driver
  + String make
  + String color
  + Double rating
  + String vin
  + ZonedDateTime dateCreated
  + String license
  + String model
  + Double ratePerKm
  + String convertible
  + setcarID(Long) void
  + toString() String
  + getcarID() Long
  + Double ratePerKm
  + String color
  + Driver driver
  + String vin
  + Double rating
  + String convertible
  + String license
  + String make
  + ZonedDateTime dateCreated
  + String model
}
class CarController {
  + CarController()
  + CarService carService
  + getAllCar(Integer, Integer) List~Car~
  + getDaysCarUsedInMonth(Integer, Integer) List~Map~String, Long~~
  + saveCar(Car) Car
  + updateCar(Long, Car) Car
  + deleteCar(Long) String
  + getSingleCar(Long) Car
  + CarService carService
}
class CarService {
  + CarService()
  + SessionFactory sessionFactory
  + deleteCar(Long) String
  + getSingleCar(Long) Car
  + saveCar(Car) Car
  + updateCar(Long, Car) Car
  + getCarUsageInMonth(Integer, Integer) List~Map~String, Long~~
  + getAllCar(Integer, Integer) List~Car~
  + SessionFactory sessionFactory
}
class Customer {
  + Customer(String, String, String, List~Booking~, ZonedDateTime)
  + Customer(Long, String, String, String, List~Booking~, ZonedDateTime)
  + Customer()
  + String address
  + ZonedDateTime dateCreated
  + String name
  + Long id
  + String phone
  + List~Booking~ bookings
  + toString() String
  + String name
  + List~Booking~ bookings
  + String address
  + ZonedDateTime dateCreated
  + Long id
  + String phone
}
class CustomerController {
  + CustomerController()
  + CustomerService customerService
  + deleteCustomer(Long) String
  + getAllCustomers(Integer, Integer, String, String, String) List~Customer~
  + updateCustomer(Long, Customer) Customer
  + getSingleCustomer(Long) Customer
  + saveCustomer(Customer) Customer
  + CustomerService customerService
}
class CustomerService {
  + CustomerService()
  + SessionFactory sessionFactory
  + getSingleCustomer(Long) Customer
  + saveCustomer(Customer) Customer
  + getAllCustomer(Integer, Integer, String, String, String) List~Customer~
  + deleteCustomer(Long) String
  + updateCustomer(Long, Customer) Customer
  + SessionFactory sessionFactory
}
class Driver {
  + Driver(Long, String, String, String, String, Double, Car, List~Booking~, ZonedDateTime)
  + Driver()
  + Driver(String, String, String, String, Double, Car, List~Booking~, ZonedDateTime)
  + String address
  + List~Booking~ bookings
  + String phone
  + Double rating
  + Car car
  + String name
  + String license
  + ZonedDateTime dateCreated
  + Long id
  + toString() String
  + String name
  + Long id
  + String phone
  + Double rating
  + String license
  + List~Booking~ bookings
  + String address
  + ZonedDateTime dateCreated
  + Car car
}
class DriverController {
  + DriverController()
  + saveDriver(Driver, Long) Driver
  + getSingleDriver(Long) Driver
  + deleteDriver(Long) String
  + updateDriver(Long, Long, Driver) Driver
  + getAllDrivers(Integer, Integer, String, String, String) List~Driver~
}
class DriverService {
  + DriverService()
  + CarService carService
  + SessionFactory sessionFactory
  + getSingleDriver(Long) Driver
  + saveDriver(Driver, Long) Driver
  + updateDriver(Driver, Long, Long) Driver
  + getAllDriver(Integer, Integer, String, String, String) List~Driver~
  + deleteDriver(Long) String
  + SessionFactory sessionFactory
  + CarService carService
}
class Group15Application {
  + Group15Application()
  + main(String[]) void
}
class Invoice {
  + Invoice(Long, Double, Booking, ZonedDateTime)
  + Invoice(Double, Booking, ZonedDateTime)
  + Invoice()
  + Booking booking
  + Long id
  + ZonedDateTime dateCreated
  + Double totalCharge
  + toString() String
  + Booking booking
  + ZonedDateTime dateCreated
  + Long id
  + Double totalCharge
}
class InvoiceController {
  + InvoiceController()
  + InvoiceService invoiceService
  + deleteInvoice(Long) String
  + getRevenueByCustomer(String, String, Long) Double
  + updateInvoice(Long, Invoice) Invoice
  + getSingleInvoice(Long) Invoice
  + getRevenueByDriver(String, String, Long) Double
  + getAllInvoices(Integer, Integer, String, String) List~Invoice~
  + saveInvoice(Invoice, Long) Invoice
  + InvoiceService invoiceService
}
class InvoiceService {
  + InvoiceService()
  + SessionFactory sessionFactory
  + BookingService bookingService
  + getSingleInvoice(Long) Invoice
  + updateInvoice(Long, Invoice) Invoice
  + getRevenueByCustomer(Long, String, String) Double
  + saveInvoice(Long, Invoice) Invoice
  + deleteInvoice(Long) String
  + getRevenueByDriver(Long, String, String) Double
  + getAllInvoices(Integer, Integer, String, String) List~Invoice~
  + SessionFactory sessionFactory
  + BookingService bookingService
}
class NotFoundException {
  + NotFoundException()
}
class WebAppInitializer {
  + WebAppInitializer()
  + Class~?~[]? servletConfigClasses
  + Class~?~[]? rootConfigClasses
  + String[] servletMappings
}

Booking "1" *--> "customer 1" Customer
Booking "1" *--> "driver 1" Driver
Booking "1" *--> "invoice 1" Invoice
BookingController "1" *--> "bookingService 1" BookingService
BookingService "1" *--> "customerService 1" CustomerService
BookingService "1" *--> "driverService 1" DriverService
BookingService  ..>  NotFoundException : «create»
Car "1" *--> "driver 1" Driver
CarController "1" *--> "carService 1" CarService
CarService  ..>  NotFoundException : «create»
Customer "1" *--> "bookings *" Booking
CustomerController "1" *--> "customerService 1" CustomerService
CustomerService  ..>  NotFoundException : «create»
Driver "1" *--> "bookings *" Booking
Driver "1" *--> "car 1" Car
DriverController "1" *--> "driverService 1" DriverService
DriverService  ..>  BadRequestException : «create»
DriverService "1" *--> "carService 1" CarService
DriverService  ..>  NotFoundException : «create»
Invoice "1" *--> "booking 1" Booking
InvoiceController "1" *--> "invoiceService 1" InvoiceService
InvoiceService  ..>  BadRequestException : «create»
InvoiceService "1" *--> "bookingService 1" BookingService
InvoiceService  ..>  NotFoundException : «create»
```
