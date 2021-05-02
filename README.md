# spring-native-function-aws-sample

- build: `mvn clean package`
- build native-image: `mvn clean package -P native-image`
- build and run native-image using [docker container images](https://docs.aws.amazon.com/lambda/latest/dg/go-image.html):
    - using coretto11: 
      `docker-compose up`
    - using graalvm: 
      `docker-compose --profile "native-image" up` (takes ~15mins on my Macbook Air)


## Troubleshooting
1. `failed to solve with frontend dockerfile.v0: failed to build LLB: executor failed running - runc did not terminate sucessfully` go to [this](https://github.com/docker/buildx/issues/426) stackoverflow thread