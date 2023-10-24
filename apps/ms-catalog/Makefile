USERNAME ?= ishakantony
APP_NAME ?= my-app

IMAGE_TAG=$(USERNAME)/$(APP_NAME):$(VERSION)

IMAGE_TAG_ARM64=$(IMAGE_TAG)-arm64
IMAGE_TAG_AMD64=$(IMAGE_TAG)-amd64

# Ensure VERSION is provided as an argument, and set it explicitly
check-version:
ifeq ($(VERSION),)
	$(error Please provide a VERSION argument)
endif

dry-run: check-version
	@echo "APP_NAME: $(APP_NAME), USERNAME: $(USERNAME), VERSION: $(VERSION)"
	@echo "IMAGE_TAG: $(IMAGE_TAG)"
	@echo "PLATFORM: linux/arm64,linux/amd64"

build: check-version dry-run
	@read -p "Are you sure you want to build? (Y/N) " answer; \
	if [ "$$answer" != "Y" ]; then \
	    echo "Build canceled."; \
	else \
			docker image build -t $(IMAGE_TAG_ARM64) . --platform linux/arm64; \
			docker image build -t $(IMAGE_TAG_AMD64) . --platform linux/amd64; \
	fi

push: check-version dry-run
	docker image push $(IMAGE_TAG_ARM64)
	docker image push $(IMAGE_TAG_AMD64)

generate-manifest: check-version
	docker manifest create --amend $(IMAGE_TAG) $(IMAGE_TAG_ARM64) $(IMAGE_TAG_AMD64)
	docker manifest push $(IMAGE_TAG)

all: build push generate-manifest
