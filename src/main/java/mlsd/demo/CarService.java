package mlsd.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class CarService {

    private List<Car> cars = new ArrayList<>();

    public CarService() {
        cars.add(new Car("11AA22", "Ferrari", 100));
        cars.add(new Car("AA11BB", "Peugeot", 50));
        cars.add(new Car("CC22DD", "Renault", 40));
    }

    

    @GetMapping("/cars")
    public List<Car> listOfCars() {
        return cars;
    }


    @GetMapping("/cars/{plateNumber}")
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception { 

    for (Car car : cars) {
        if (car.getPlateNumber().equals(plateNumber)) {
            return car;
        }
    }

    throw new Exception("Car not found");
    }


    
     
     




    @GetMapping("/cars/Unrented")
    public List<Car> listOfUnRentedCars() {

        List<Car> unrentedCars = new ArrayList<>();

        for (Car car : cars) {
            if (!car.isRented()) {
                unrentedCars.add(car);
            }
        }

        return unrentedCars;
    }









    @PutMapping("/voiture/{plateNumber}")
    public void rentOrGetBack(
        @PathVariable("plateNumber") String plateNumber,
        @RequestParam(value = "rent", required = true) boolean rent) throws Exception {

        for (Car car : cars) {

            if (car.getPlateNumber().equals(plateNumber)) {
                car.setRented(rent);
                return;
            }
        }

        throw new Exception("Car not found");
    }



     






    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(
        @PathVariable("plateNumber") String plateNumber,
        @RequestParam(value = "rent", required = true) boolean rent,
        @RequestBody Dates dates) {

        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {

                car.setRented(rent);

                if (rent) {
                    car.setDates(dates);
                }

                return;
            }
        }
    }






}