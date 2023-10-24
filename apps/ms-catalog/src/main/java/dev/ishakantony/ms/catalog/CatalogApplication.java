package dev.ishakantony.ms.catalog;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

  static record Product(int id, String name) {}

  @RestController
  static class HelloController {

    @GetMapping("/products")
    public List<Product> products() {
      return Arrays.asList(
        new Product(1, "Smartphone"),
        new Product(2, "Refrigerator"),
        new Product(3, "Washing Machine"),
        new Product(4, "Microwave Oven"),
        new Product(5, "Coffee Maker"),
        new Product(6, "Blender"),
        new Product(7, "Toaster"),
        new Product(8, "Vacuum Cleaner"),
        new Product(9, "Air Conditioner"),
        new Product(10, "Dishwasher"),
        new Product(11, "Electric Kettle"),
        new Product(12, "Food Processor"),
        new Product(13, "Hair Dryer"),
        new Product(14, "Curling Iron"),
        new Product(15, "Iron"),
        new Product(16, "Clothes Steamer"),
        new Product(17, "Espresso Machine"),
        new Product(18, "Stand Mixer"),
        new Product(19, "Rice Cooker"),
        new Product(20, "Slow Cooker"),
        new Product(21, "Air Purifier"),
        new Product(22, "Soundbar"),
        new Product(23, "Smartwatch"),
        new Product(24, "Fitness Tracker"),
        new Product(25, "Headphones"),
        new Product(26, "Digital Camera"),
        new Product(27, "Drone"),
        new Product(28, "Gaming Console"),
        new Product(29, "Bluetooth Speaker"),
        new Product(30, "E-reader"),
        new Product(31, "Tablet"),
        new Product(32, "Desktop Computer"),
        new Product(33, "Printer"),
        new Product(34, "Monitor"),
        new Product(35, "Gaming Mouse"),
        new Product(36, "Mechanical Keyboard"),
        new Product(37, "Graphics Card"),
        new Product(38, "External Hard Drive"),
        new Product(39, "Webcam"),
        new Product(40, "Projector"),
        new Product(41, "Home Theater System"),
        new Product(42, "Electric Toothbrush"),
        new Product(43, "Shaver"),
        new Product(44, "Hair Straightener"),
        new Product(45, "Hair Clippers"),
        new Product(46, "Beard Trimmer"),
        new Product(47, "Electric Razor"),
        new Product(48, "Smart Lock"),
        new Product(49, "Robot Vacuum"),
        new Product(50, "Security Camera"),
        new Product(51, "Baby Monitor"),
        new Product(52, "Wi-Fi Router"),
        new Product(53, "NAS (Network Attached Storage)"),
        new Product(54, "Cordless Phone"),
        new Product(55, "LED Bulbs"),
        new Product(56, "Smart Thermostat"),
        new Product(57, "Coffee Grinder"),
        new Product(58, "Air Fryer"),
        new Product(59, "Hand Mixer"),
        new Product(60, "Juicer"),
        new Product(61, "Electric Griddle"),
        new Product(62, "Waffle Maker"),
        new Product(63, "Food Dehydrator"),
        new Product(64, "Popcorn Maker"),
        new Product(65, "Sewing Machine"),
        new Product(66, "Drill"),
        new Product(67, "Circular Saw"),
        new Product(68, "Power Drill"),
        new Product(69, "Jigsaw"),
        new Product(70, "Chainsaw"),
        new Product(71, "Leaf Blower"),
        new Product(72, "Lawn Mower"),
        new Product(73, "Pressure Washer"),
        new Product(74, "Hedge Trimmer"),
        new Product(75, "Bike"),
        new Product(76, "Electric Scooter"),
        new Product(77, "Skateboard"),
        new Product(78, "Hoverboard"),
        new Product(79, "Treadmill"),
        new Product(80, "Elliptical Trainer"),
        new Product(81, "Rowing Machine"),
        new Product(82, "Yoga Mat"),
        new Product(83, "Dumbbells"),
        new Product(84, "Resistance Bands"),
        new Product(85, "Jump Rope"),
        new Product(86, "Camping Tent"),
        new Product(87, "Sleeping Bag"),
        new Product(88, "Backpack"),
        new Product(89, "GPS Navigator"),
        new Product(90, "Binoculars"),
        new Product(91, "Telescope"),
        new Product(92, "Camping Stove"),
        new Product(93, "Fishing Rod"),
        new Product(94, "Golf Clubs"),
        new Product(95, "Snowboard"),
        new Product(96, "Ski Boots"),
        new Product(97, "Kayak"),
        new Product(98, "Surfboard"),
        new Product(99, "Bike Helmet"),
        new Product(100, "Roller Skates")
      );
    }

  }

}
