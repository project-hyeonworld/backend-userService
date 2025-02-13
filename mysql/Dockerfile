# Use the official MySQL image as the base image
FROM mysql:8.0

# Set environment variables
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes
ENV MYSQL_ROOT_PASSWORD={MYSQL_ROOT_PASSWORD}
ENV MYSQL_DATABASE={MYSQL_DATABASE}

# Copy initialization SQL script into the container
#COPY init.sql /docker-entrypoint-initdb.d/

# Expose port 3306 (default MySQL port)
EXPOSE 3306