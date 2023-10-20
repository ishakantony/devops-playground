package main

import (
	"fmt"
	"log/slog"
	"math/rand"
	"os"
	"time"
)

func main() {
	log := slog.New(slog.NewJSONHandler(os.Stdout, nil))

	for {
		// Generate a random number between 0 and 99.
		randomNumber := rand.Intn(100)

		if randomNumber < 60 {
			log.Info(generateRandomOrderLog())
		} else if randomNumber < 80 {
			log.Warn("Warning from the app!")
		} else if randomNumber < 90 {
			log.Error("Error from the app!")
		} else {
			log.Debug("Debug message from the app!")
		}

		time.Sleep(time.Duration(rand.Intn(1000)) * time.Millisecond)
	}
}

func generateRandomOrderLog() string {
	// List of possible order statuses and products.
	orderStatus := []string{"New", "Processing", "Shipped", "Delivered"}
	products := []string{"Laptop", "Microwave", "Stove", "Door", "Blender"}

	// Generate random values.
	status := orderStatus[rand.Intn(len(orderStatus))]
	product := products[rand.Intn(len(products))]
	quantity := rand.Intn(10) + 1 // Random quantity between 1 and 10.

	// Create a log message with random values.
	logMessage := fmt.Sprintf("Order received: Status=%s, Product=%s, Quantity=%d", status, product, quantity)

	return logMessage
}
