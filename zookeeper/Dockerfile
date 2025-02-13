FROM {DOCKER_ZOOKEEPER_IMAGE_NAME}:{DOCKER_ZOOKEEPER_IMAGE_TAG}

RUN apt-get update && apt-get install -y openssh-server
RUN mkdir -p /var/run/sshd && chmod 0755 /var/run/sshd

EXPOSE {DOCKER_ZOOKEEPER_PORT}
