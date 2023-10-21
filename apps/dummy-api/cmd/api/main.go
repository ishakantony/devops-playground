package main

import (
	"fmt"
	"math/rand"
	"os"
	"time"

	"github.com/gofiber/fiber/v2"
)

func main() {
	version := os.Getenv("VERSION")

	if version == "" {
		version = "MISSING VERSION"
	}

	app := fiber.New()

	app.Get("/", func(c *fiber.Ctx) error {

		if version == "V1" {
			return v1(c, version)
		}

		if version == "V2" {
			return v2(c, version)
		}

		time.Sleep(time.Duration(rand.Intn(200)) * time.Millisecond)
		return c.SendString(fmt.Sprintf("App version: [%s]\n", version))
	})

	app.Listen(":10000")
}

func v1(c *fiber.Ctx, version string) error {
	time.Sleep(time.Duration(rand.Intn(3000)) * time.Millisecond)
	return c.Status(200).SendString(fmt.Sprintf("App version: [%s]\n", version))
}

func v2(c *fiber.Ctx, version string) error {
	errStatusCodes := []int{400, 401, 403, 500, 502, 503, 504}
	success := rand.Intn(10) > 3

	if success {
		time.Sleep(time.Duration(rand.Intn(1000)) * time.Millisecond)
		return c.Status(200).SendString(fmt.Sprintf("App version: [%s]\n", version))
	} else {
		time.Sleep(time.Duration((1000 + rand.Intn(3000))) * time.Millisecond)
		statusCode := errStatusCodes[rand.Intn(len(errStatusCodes))]
		return c.Status(statusCode).SendString(fmt.Sprintf("Something went wrong with version: [%s]\n", version))
	}
}
